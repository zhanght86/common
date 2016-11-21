package com.sjdf.platform.common.utils;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.zip.GZIPOutputStream;

/**
 * 文件操作辅助类
 *
 * @author 王正伟
 */
public abstract class FileUtils {
    private static SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(FileUtils.class);
    /** excel的sheet的最大行数 */
    public static final int EXCEL_SHEET_MAX_ROWNUM = 65000;
    public static final String FILE_SEPARATOR = "/";
    public static final String FILE_ENCODE_GBK = "GBK";

    /**
     * 格式化目录路径
     *
     * @param dirPath 目录路径
     * @return 格式化后的统一路径
     */
    public static String formatDirPath(String dirPath) {
        String path = dirPath;
        if (PlatformUtils.hasText(path)) {
            path = path.replace("\\", FileUtils.FILE_SEPARATOR);
            if (!path.endsWith(FileUtils.FILE_SEPARATOR)) {
                path += FileUtils.FILE_SEPARATOR;
            }
        }
        return path;
    }

    /**
     * 文件复制, 通道方式
     *
     * @param resourceFile 源文件
     * @param targetFile   目标文件
     */
    public static void copy4channel(File resourceFile, File targetFile) {
        if (resourceFile == null || targetFile == null) {
            return;
        }

        if (resourceFile.isDirectory()) {
            if (!targetFile.exists()) {
                targetFile.mkdir();
            }
            File[] files = resourceFile.listFiles();
            if (files != null) {
                StringBuilder fileName;
                for (File file : files) {
                    fileName = new StringBuilder(targetFile.getAbsolutePath()).append("/").append(file.getName());
                    copy4channel(file, new File(fileName.toString()));
                }
            }
        } else {
            try (FileChannel inC = new FileInputStream(resourceFile).getChannel(); FileChannel outC = new FileOutputStream(targetFile).getChannel()) {
                ByteBuffer buffer = ByteBuffer.allocateDirect(CommonPlatformConstant.LENGTH_1024);
                while (inC.read(buffer) != -1) {
                    buffer.flip();
                    outC.write(buffer);
                    buffer.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("文件复制失败", e);
                throw new RuntimeException("文件复制失败", e);
            }
        }
    }

    /**
     * @return 获得一个文件的字节数组.
     */
    public static byte[] getBytes(File file) {
        long fileLengthL = file.length();
        if (fileLengthL > Integer.MAX_VALUE) {
            throw new RuntimeException("The file is too large to getBytes.");
        }

        int fileLength = (int) fileLengthL;
        byte[] fileBytes = new byte[fileLength];
        int startIndex = 0;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            while (startIndex < fileLength) {
                bis.read(fileBytes, startIndex, startIndex + CommonPlatformConstant.LENGTH_1024 > fileLength ? fileLength - startIndex : CommonPlatformConstant.LENGTH_1024);
                startIndex += CommonPlatformConstant.LENGTH_1024;
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("读取文件内容失败", e);
            throw new RuntimeException("读取文件内容失败", e);
        }

        return fileBytes;
    }

    /**
     * @param filePath 文件路径
     * @return 内容
     * 读取文本文件, 并返回其内容
     */
    public static String readTxtFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }

        StringBuilder content = new StringBuilder();
        String str;
        try (BufferedReader bd = new BufferedReader(new FileReader(file))) {
            while ((str = bd.readLine()) != null) {
                content.append(str).append("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件读取失败", e);
            throw new RuntimeException("文件读取失败");
        }

        return content.toString();
    }

    /**
     * 将文本内容写入文件
     *
     * @param content  文件内容
     * @param fileName 待写入文件的全路径
     */
    public static void writeTxt2File(String content, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件写入失败!", e);
            throw new RuntimeException("文件写入失败!");
        }
    }

    /**
     * @param filePath 删除指定的文件或文件夹
     */
    public static void delFile(String filePath) {
        // 定义文件路径
        File f = new File(filePath);
        if (!f.exists()) {
            return;
        }
        if (f.isDirectory()) {
            // 判断是文件还是目录
            File[] delFile = f.listFiles();
            if (delFile != null && delFile.length > 0) {
                // 若有则把文件放进数组，并判断是否有下级目录
                for (File temp : delFile) {
                    if (temp.isDirectory()) {
                        // 递归调用del方法并取得子目录路径
                        delFile(temp.getAbsolutePath());
                    } else {
                        // 删除文件
                        temp.delete();
                    }
                }
            }
            // 若目录下没有文件则直接删除
            f.delete();
        } else {
            // 不是文件目录则直接删除
            f.delete();
        }
    }

    /**
     * @param file 文件
     * @return 获取指定文件的大小
     */
    public static long fileSize(File file) {
        if (!file.exists()) {
            return 0;
        }
        long size = 0;
        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            if (fileList != null) {
                for (File aFileList : fileList) {
                    if (aFileList.isDirectory()) {
                        size += fileSize(aFileList);
                    } else {
                        size += aFileList.length();
                    }
                }
            }
        } else {
            size += file.length();
        }
        return size;
    }

    /**
     * @param fileSize 文件大小
     * @return 格式化文件大小
     */
    public static String formetFileSize(long fileSize) {
        DecimalFormat df = new DecimalFormat("0.00");
        String fileSizeString;
        if (fileSize < CommonPlatformConstant.LENGTH_1024) {
            fileSizeString = df.format((double) fileSize) + "B";
        } else if (fileSize < CommonPlatformConstant.LENGTH_1048576) {
            fileSizeString = df.format((double) fileSize / CommonPlatformConstant.LENGTH_1024) + "K";
        } else if (fileSize < CommonPlatformConstant.LENGTH_1073741824) {
            fileSizeString = df.format((double) fileSize / CommonPlatformConstant.LENGTH_1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileSize / CommonPlatformConstant.LENGTH_1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * @param file 文件
     * @return 转换文件大小
     */
    public static String formetFileSize(File file) {
        return formetFileSize(fileSize(file));
    }

    /**
     * @param inputFileName 压缩文件名称
     * @param paths         文件绝对路径
     * @throws Exception 文件压缩
     */
    public static void zipCompression(String inputFileName, String... paths) throws Exception {
        if (paths == null) {
            throw new NullPointerException("paths is null");
        }

        File inputFile = new File(inputFileName);
        try (ZipOutputStream zipos = new ZipOutputStream(new FileOutputStream(inputFile))) {

            // 这里将buf加大,以用于生成时增加其缓冲,生成速度加快
            Field field = ZipOutputStream.class.getDeclaredField("buf");
            field.setAccessible(true);
            field.set(zipos, new byte[CommonPlatformConstant.LENGTH_1024 * CommonPlatformConstant.LENGTH_8]);
            // 设置编码
            zipos.setEncoding(System.getProperty("file.encoding"));

            if (paths.length == 1) {
                File file = new File(paths[0]);
                if (file.exists()) {
                    if (file.isDirectory()) {
                        File[] subs = file.listFiles();
                        if (subs != null) {
                            for (File sub : subs) {
                                zipCompression(zipos, sub, sub.getName());
                            }
                        }
                    } else {
                        zipCompression(zipos, file, file.getName());
                    }
                }
            } else {
                for (String filePath : paths) {
                    File file = new File(filePath);
                    if (file.exists()) {
                        zipCompression(zipos, file, file.getName());
                    }
                }
            }
        }
    }

    /**
     * 解压指定的zip文件到指定的目录下
     *
     * @param zipFilePath    待解压的文件
     * @param releaseDirPath 释放目录
     */
    public static void zipDecompress(String zipFilePath, String releaseDirPath) throws Exception {
        String releasePath = formatDirPath(releaseDirPath);

        ZipFile zipFile = null;
        try {
            ZipEntry zipEntry;
            String zipEntryName;
            String[] zipEntryNameArray;

            zipFile = new ZipFile(zipFilePath);
            Enumeration<ZipEntry> enumeration = zipFile.getEntries();
            while (enumeration.hasMoreElements()) {
                zipEntry = enumeration.nextElement();
                zipEntryName = new String(zipEntry.getRawName(), System.getProperty("file.encoding"));
                zipEntryNameArray = zipEntryName.split(FILE_SEPARATOR);

                String path = releasePath;
                File root = new File(path);
                if (!root.exists()) {
                    root.mkdir();
                }

                for (int i = 0; i < zipEntryNameArray.length; i++) {
                    if (i < zipEntryNameArray.length - 1) {
                        path = path + File.separator + zipEntryNameArray[i];
                        new File(path).mkdir();
                    } else {
                        if (zipEntryName.endsWith(FILE_SEPARATOR)) {
                            new File(releasePath + zipEntryName).mkdir();
                        } else {
                            try (InputStream is = zipFile.getInputStream(zipEntry); FileOutputStream fos = new FileOutputStream(new File(releasePath + zipEntryName))) {
                                byte[] buf = new byte[CommonPlatformConstant.LENGTH_1024];
                                int len;
                                while ((len = is.read(buf)) > 0) {
                                    fos.write(buf, 0, len);
                                }
                            }
                        }
                    }
                }
            }
        } finally {
            if (zipFile != null) {
                zipFile.close();
            }
        }
    }

    /**
     * @param url    远程地址:http://download.51web.com/eissclient.rar
     * @param target 存放的目标地址:d:\wwwroot\sitename\
     *               远程文件下载
     */
    public static void fileDownload(String url, String target) {
        logger.info("远程模板下载地址:" + url);
        logger.info("远程模板存放路径:" + target);
        if (!URLHelper.urlExist(url)) {
            logger.info("远程模板下载地址,不存在");
            throw new RuntimeException("远程模板下载地址,不存在!");
        }

        File targetFile = new File(target);
        int length;
        byte[] buffer = new byte[CommonPlatformConstant.LENGTH_5120];

        try (BufferedInputStream bis = new BufferedInputStream(new URL(url).openStream()); FileOutputStream fos = new FileOutputStream(targetFile)) {
            while ((length = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("远程文件下载失败", e);
            throw new RuntimeException("远程文件下载失败!");
        }
    }

    private static void zipCompression(ZipOutputStream out, File f, String basePath) throws Exception {
        String base = basePath;
        if (f.isDirectory()) {
            // 判断是否为目录
            File[] fl = f.listFiles();
            ZipEntry zipEntry = new ZipEntry(base + FILE_SEPARATOR);
            // 解决linux乱码
            zipEntry.setUnixMode(CommonPlatformConstant.LENGTH_755);
            out.putNextEntry(zipEntry);
            base = base.length() == 0 ? "" : base + FILE_SEPARATOR;
            if (fl != null) {
                for (File aFl : fl) {
                    zipCompression(out, aFl, base + aFl.getName());
                }
            }
        } else { // 压缩目录中的所有文件
            ZipEntry zipEntry = new ZipEntry(base);
            // 解决linux乱码
            zipEntry.setUnixMode(CommonPlatformConstant.LENGTH_644);
            out.putNextEntry(zipEntry);
            out.write(getBytes(f));
        }
    }

    /**
     * 创建目录
     *
     * @param dir 目录
     */
    public static void mkdirs(String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                mkdirs(parent.getPath());
            }

            file.mkdir();
        }
    }

    /**
     * @param path 创建文件
     */
    public static File createFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                mkdirs(parent.getPath());
            }

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
        }
        return file;
    }

    /**
     * 保存指定输入流到具体文件
     *
     * @param filePath 保存到的文件路径
     * @param is       输入流
     */
    public static File saveInputStream(String filePath, InputStream is) {
        File storefile = new File(filePath);
        saveInputStream(storefile, is);
        return storefile;
    }

    /**
     * 保存指定输入流到具体文件
     *
     * @param storefile 保存到的文件路径
     * @param is        输入流
     */
    public static void saveInputStream(File storefile, InputStream is) {
        int c;
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(storefile)); BufferedInputStream bis = new BufferedInputStream(is)) {
            while ((c = bis.read()) != -1) {
                bos.write(c);
                bos.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("文件保存失败!", e);
        }
    }

    /**
     * java Gzip 压缩文件
     *
     * @param inFileName 单个文件路径
     */
    public static void gzSingleFile(String inFileName) {
        String outFileName = inFileName + ".gz";
        try (GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(outFileName)); FileInputStream in = new FileInputStream(inFileName)) {
            byte[] buf = new byte[CommonPlatformConstant.LENGTH_1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.finish();
        } catch (IOException ignore) {
            ignore.printStackTrace();
            logger.error("gz compression " + outFileName, ignore);
        }
    }
}