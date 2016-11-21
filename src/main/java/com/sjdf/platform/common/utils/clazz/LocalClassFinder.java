package com.sjdf.platform.common.utils.clazz;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Create at 2013年12月23日 上午11:41:36
 * 获得指定接口或者抽象类的所有实现类(包括jar中的类)
 *
 * @author KETQI
 */
public class LocalClassFinder {
    private SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(LocalClassFinder.class);
    private static final String DEFAULT_PACKAGE_NAME = "com.sjdf";
    private String loadPackages;
    private List<File> fileList;

    public LocalClassFinder() {
        this.loadPackages = DEFAULT_PACKAGE_NAME;
    }

    public LocalClassFinder(String loadPackages) {
        this.loadPackages = loadPackages;
    }

    /**
     * 获取指定类型的class
     *
     * @param clazz 匹配类型
     * @return 类型列表
     */
    @SuppressWarnings("resource")
    public <T> List<Class<? extends T>> findSubClass(Class<T> clazz) {
        List<File> list = this.fileList;
        if (list == null) {
            list = listPaths();
        }

        Set<Class<? extends T>> classList = new HashSet<>();
        for (File file : list) {
            // 如果是以文件的形式保存在服务器上
            if (file.isDirectory()) {
                // 获取包的物理路径
                dirWalker(file, classList, clazz);
            } else {
                // 尝试是否是jar文件
                // 获取jar
                JarFile jar;
                try {
                    jar = new JarFile(file);
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                    // 有可能不是一个jar
                    continue;
                }
                // 从此jar包 得到一个枚举类
                Enumeration<JarEntry> entries = jar.entries();
                // 同样的进行循环迭代
                while (entries.hasMoreElements()) {
                    // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    // 如果是以/开头的
                    if (name.charAt(0) == '/') {
                        // 获取后面的字符串
                        name = name.substring(1);
                    }
                    // 如果前半部分和定义的包名相同
                    if (name.endsWith(".class") && !entry.isDirectory()) {
                        name = name.replace("/", ".").substring(0, name.lastIndexOf("."));
                        if (name.contains(loadPackages)) {
                            Class<T> c = isClass(clazz, name);
                            if (c != null) {
                                classList.add(c);
                            }
                        }
                    }
                }
            }
        }

        return new ArrayList<>(classList);
    }

    /**
     * 获取项目的path下所有的文件夹和文件
     *
     * @return 文件列表
     */
    private List<File> listPaths() {
        Set<File> fileSet = new HashSet<>(CommonPlatformConstant.LENGTH_32);
        String jars = System.getProperty("java.class.path");
        if (jars == null) {
            logger.error("java.class.path is null!");
            return Collections.emptyList();
        }
        logger.info(jars);

        URL root = LocalClassFinder.class.getClassLoader().getResource("");
        if (root == null) {
            logger.error("path root is null!");
            return Collections.emptyList();
        }

        String path;
        try {
            path = URLDecoder.decode(root.getFile(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyList();
        }
        logger.info(path);

        // classes
        File dir = new File(path);
        fileSet.add(dir);

        // lib
        File lib = new File(dir.getParentFile(), "lib");
        for (File file : lib.listFiles()) {
            fileSet.add(file);
        }

        // java.class.path
        String[] array = jars.split(";");
        for (String s : array) {
            if (s == null) {
                continue;
            }
            File f = new File(s);
            if (f.exists()) {
                fileSet.add(f);
            } else {// 有些jar就在系统目录下,省略了路径,要加上
                File jar = new File(dir, s);
                if (jar.exists()) {
                    fileSet.add(jar);
                }
            }
        }

        this.fileList = new ArrayList<>(fileSet);
        return this.fileList;
    }

    /**
     * 递归遍历文件夹下所有文件
     *
     * @param file  待遍历的文件
     * @param list  匹配的class列表
     * @param clazz 匹配标准class
     */
    private <T> void dirWalker(File file, Set<Class<? extends T>> list, Class<T> clazz) {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    dirWalker(f, list, clazz);
                }
            } else {
                Class<T> c = loadClassByFile(file, clazz);
                if (c != null) {
                    list.add(c);
                }
            }
        }
    }

    /**
     * 从文件加载类
     *
     * @param clazz 指定匹配类型
     * @param file  文件
     * @return 类或者null
     */
    private <T> Class<T> loadClassByFile(File file, Class<T> clazz) {
        if (!file.exists()) {
            return null;
        }

        String name = file.getName();
        // class
        if (name.endsWith(".class")) {
            String ap = file.getAbsolutePath();
            ap = ap.replace(File.separatorChar, '.');
            if (!ap.contains(loadPackages)) {
                return null;
            }
            name = ap.substring(ap.indexOf(loadPackages) + loadPackages.length());
            if (name.startsWith(".")) {
                name = name.substring(1);
            }
            String path = loadPackages + "." + name.substring(0, name.lastIndexOf("."));
            return isClass(clazz, path);
        }
        return null;
    }

    /**
     * 判断类型是否匹配
     *
     * @param clazz 指定的类型
     * @param name  待判断的class的全名称
     * @return 类或者null
     */
    @SuppressWarnings("unchecked")
    private <T> Class<T> isClass(Class<T> clazz, String name) {
        try {
            Class<?> c = LocalClassFinder.class.getClassLoader().loadClass(name);
            // c必须是public,非接口,非抽象类,类型是clazz
            if (c != null && !c.isInterface() && !Modifier.isAbstract(c.getModifiers()) && Modifier.isPublic(c.getModifiers()) && clazz.isAssignableFrom(c)) {
                return (Class<T>) c;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return null;
    }
}
