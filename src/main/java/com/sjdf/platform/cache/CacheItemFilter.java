package com.sjdf.platform.cache;

/**
 * 缓存条目过滤
 * User: ketqi
 * Date: 2015-02-09 13:48
 */
public interface CacheItemFilter<T> {
    /**
     * 缓存条目过滤(false:过滤掉;true:滞留)
     *
     * @param condition 过滤条件
     * @return bool
     */
    boolean exist(T condition);
}
