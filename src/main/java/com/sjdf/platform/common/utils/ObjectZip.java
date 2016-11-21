package com.sjdf.platform.common.utils;

import com.sjdf.platform.CommonPlatformConstant;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Create at 2012-08-24
 * <p/>
 * 对象压缩工具
 *
 * @author Chen Mohan
 */
public abstract class ObjectZip {

    /** 字节数组转换为对象 */
    public static Object unZip(byte[] bytes) throws Exception {

        Object object;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(byteArrayInputStream);

        // 解压缩
        ZipInputStream zipInputStream = new ZipInputStream(bufferedInputStream);
        zipInputStream.getNextEntry();

        // 反序列化
        try (ObjectInputStream objectInputStream = new ObjectInputStream(zipInputStream)) {
            object = objectInputStream.readObject();
        }

        return object;
    }

    /** 对象转换为字节数组 */
    public static byte[] zip(Object o) throws Exception {

        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();

        BufferedOutputStream bufferedOutputStream1 = new BufferedOutputStream(byteArrayOutputStream1);
        BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(byteArrayOutputStream2);

        // 序列化
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream1)) {
            objectOutputStream.writeObject(o);
        }

        // 进行压缩
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream2)) {
            zipOutputStream.setLevel(CommonPlatformConstant.LENGTH_9);
            zipOutputStream.putNextEntry(new ZipEntry("data.list"));
            zipOutputStream.write(byteArrayOutputStream1.toByteArray());
            zipOutputStream.closeEntry();
        }

        return byteArrayOutputStream2.toByteArray();
    }
}
