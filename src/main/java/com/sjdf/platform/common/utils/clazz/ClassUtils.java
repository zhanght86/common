package com.sjdf.platform.common.utils.clazz;

import com.sjdf.platform.CommonPlatformConstant;
import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.hibernate.proxy.HibernateProxyHelper;

import java.lang.reflect.Field;
import java.util.*;

/**
 * class辅助工具
 * Create at 2012-04-05
 *
 * @author 王正伟
 */
public abstract class ClassUtils {
    private static final SJDFLogger LOGGER = SJDFLoggerFactory.getSJDFLogger(ClassUtils.class);

    @SuppressWarnings("unchecked")
    public static <T> Class<T> forname(String className) {
        try {
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException ignore) {
            ignore.printStackTrace();
            LOGGER.error(ignore.getMessage(), ignore);
        }
        return null;
    }

    /** 对于hibernate代理对象,找到其真实对象 */
    public static Class<?> findRealClass(Object object) {
        if (object instanceof BaseBean) {
            return HibernateProxyHelper.getClassWithoutInitializingProxy(object);
        }
        return object.getClass();
    }

    /**
     * 批量创建实例
     *
     * @param tCollection clazz列表
     * @return 实例列表
     */
    public static <T> List<T> initializeClass(Collection<Class<? extends T>> tCollection) {
        List<T> clazzList = new ArrayList<>(tCollection.size());
        for (Class<? extends T> clazz : tCollection) {
            try {
                T t = clazz.newInstance();
                clazzList.add(t);
            } catch (Exception ignore) {
                ignore.printStackTrace();
                LOGGER.error(ignore.getMessage(), ignore);
            }
        }
        return clazzList;
    }

    /**
     * 获取指定class的所有属性
     *
     * @param clazz class
     * @return 属性列表
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> list = new ArrayList<>(CommonPlatformConstant.LENGTH_32);
        getAllFields(clazz, list);
        return list;
    }

    /**
     * 将实例转换为map
     *
     * @param obj 待转换的对象
     * @return map
     */
    public static Map<String, Object> toMap(Object obj) {
        List<Field> fieldList = ClassUtils.getAllFields(obj.getClass());
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            for (Field field : fieldList) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    private static void getAllFields(Class<?> clazz, List<Field> list) {
        Field[] fields = clazz.getDeclaredFields();
        list.addAll(Arrays.asList(fields));
        Class<?> cls = clazz.getSuperclass();
        if (cls != null) {
            getAllFields(cls, list);
        }
    }
}
