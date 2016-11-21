package com.sjdf.platform.common.utils;

import java.util.Collection;

/**
 * User: ketqi
 * Date: 2013-04-15 15:22
 * equals辅助器,判断两个对象是否相等
 */
public abstract class EqualsUtils {
    /** 判断两个值在都有值的情况下是否是不相等的,如果任一方为null，则直接返回true */
    public static boolean notEqualsWithNotNull(String source, String other) {
        return !(source == null && other == null) && (source == null || other == null || !source.equals(other));
    }

    /** 判断两个集合是否是相同的,在两个集合中,不再判断集合中的值的序列,即并不要求某个值在两个集合中的顺序是一样的,只关注内容 */
    public static <T> boolean equalsWithNotSequence(Collection<T> source, Collection<T> other) {
        return source.size() == other.size() && source.containsAll(other);
    }
}
