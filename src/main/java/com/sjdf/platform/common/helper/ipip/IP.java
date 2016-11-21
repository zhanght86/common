package com.sjdf.platform.common.helper.ipip;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class IP {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(IP.class);

    public static String randomIp() {
        Random r = new Random();
        StringBuffer str = new StringBuffer();
        str.append(r.nextInt(CommonPlatformConstant.LENGTH_1000 * CommonPlatformConstant.LENGTH_1000) % CommonPlatformConstant.LENGTH_255);
        str.append(".");
        str.append(r.nextInt(CommonPlatformConstant.LENGTH_1000 * CommonPlatformConstant.LENGTH_1000) % CommonPlatformConstant.LENGTH_255);
        str.append(".");
        str.append(r.nextInt(CommonPlatformConstant.LENGTH_1000 * CommonPlatformConstant.LENGTH_1000) % CommonPlatformConstant.LENGTH_255);
        str.append(".");
        str.append(0);

        return str.toString();
    }

    private static boolean enableFileWatch = false;
    private static int offset;
    private static int[] index = new int[CommonPlatformConstant.LENGTH_256];
    private static ByteBuffer dataBuffer;
    private static ByteBuffer indexBuffer;
    private static Long lastModifyTime = 0L;
    private static File ipFile;
    private static ReentrantLock lock = new ReentrantLock();

    public static void load(String filename) {
        ipFile = new File(filename);
        load();
        if (enableFileWatch) {
            watch();
        }
    }

    public static void load(String filename, boolean strict) throws Exception {
        ipFile = new File(filename);
        if (strict) {
            int contentLength = Long.valueOf(ipFile.length()).intValue();
            if (contentLength < CommonPlatformConstant.LENGTH_512 * CommonPlatformConstant.LENGTH_1024) {
                throw new Exception("ip data file error.");
            }
        }
        load();
        if (enableFileWatch) {
            watch();
        }
    }

    public static String[] find(String ip) {
        int ipPrefixValue = new Integer(ip.substring(0, ip.indexOf(".")));
        long ip2LongValue = ip2long(ip);
        int start = index[ipPrefixValue];
        int maxCompLen = offset - CommonPlatformConstant.LENGTH_1024 - CommonPlatformConstant.LENGTH_4;
        long indexOffset = -1;
        int indexLength = -1;
        byte b = 0;
        for (start = start * CommonPlatformConstant.LENGTH_8 + CommonPlatformConstant.LENGTH_1024; start < maxCompLen; start += CommonPlatformConstant.LENGTH_8) {
            if (int2long(indexBuffer.getInt(start)) >= ip2LongValue) {
                indexOffset = bytesToLong(b, indexBuffer.get(start + CommonPlatformConstant.LENGTH_6), indexBuffer.get(start + CommonPlatformConstant.LENGTH_5), indexBuffer.get(start + CommonPlatformConstant.LENGTH_4));
                indexLength = 0xFF & indexBuffer.get(start + CommonPlatformConstant.LENGTH_7);
                break;
            }
        }

        byte[] areaBytes;

        lock.lock();
        try {
            dataBuffer.position(offset + (int) indexOffset - CommonPlatformConstant.LENGTH_1024);
            areaBytes = new byte[indexLength];
            dataBuffer.get(areaBytes, 0, indexLength);
        } finally {
            lock.unlock();
        }

        return new String(areaBytes, Charset.forName("UTF-8")).split("\t", -1);
    }

    private static void watch() {
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long time = ipFile.lastModified();
                if (time > lastModifyTime) {
                    lastModifyTime = time;
                    load();
                }
            }
        }, 1000L, 5000L, TimeUnit.MILLISECONDS);
    }

    private static void load() {
        lastModifyTime = ipFile.lastModified();
        FileInputStream fin = null;
        lock.lock();
        try {
            dataBuffer = ByteBuffer.allocate(Long.valueOf(ipFile.length()).intValue());
            fin = new FileInputStream(ipFile);
            int readBytesLength;
            byte[] chunk = new byte[CommonPlatformConstant.LENGTH_4096];
            while (fin.available() > 0) {
                readBytesLength = fin.read(chunk);
                dataBuffer.put(chunk, 0, readBytesLength);
            }
            dataBuffer.position(0);
            int indexLength = dataBuffer.getInt();
            byte[] indexBytes = new byte[indexLength];
            dataBuffer.get(indexBytes, 0, indexLength - CommonPlatformConstant.LENGTH_4);
            indexBuffer = ByteBuffer.wrap(indexBytes);
            indexBuffer.order(ByteOrder.LITTLE_ENDIAN);
            offset = indexLength;

            int loop = 0;
            while (loop++ < CommonPlatformConstant.LENGTH_256) {
                index[loop - 1] = indexBuffer.getInt();
            }
            indexBuffer.order(ByteOrder.BIG_ENDIAN);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            LOGGER.error(ioe.getMessage(), ioe);
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage(), e);
            }
            lock.unlock();
        }
    }

    private static long bytesToLong(byte a, byte b, byte c, byte d) {
        return int2long(((a & 0xff) << CommonPlatformConstant.LENGTH_24) | ((b & 0xff) << CommonPlatformConstant.LENGTH_16) | ((c & 0xff) << CommonPlatformConstant.LENGTH_8) | (d & 0xff));
    }

    private static int str2Ip(String ip) {
        String[] ss = ip.split("\\.");
        int a, b, c, d;
        a = Integer.parseInt(ss[0]);
        b = Integer.parseInt(ss[1]);
        c = Integer.parseInt(ss[CommonPlatformConstant.LENGTH_2]);
        d = Integer.parseInt(ss[CommonPlatformConstant.LENGTH_3]);
        return (a << CommonPlatformConstant.LENGTH_24) | (b << CommonPlatformConstant.LENGTH_16) | (c << CommonPlatformConstant.LENGTH_8) | d;
    }

    private static long ip2long(String ip) {
        return int2long(str2Ip(ip));
    }

    private static long int2long(int i) {
        long l = i & 0x7fffffffL;
        if (i < 0) {
            l |= 0x080000000L;
        }
        return l;
    }
}
