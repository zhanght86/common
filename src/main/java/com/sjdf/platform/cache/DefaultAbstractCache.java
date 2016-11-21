package com.sjdf.platform.cache;

import com.sjdf.platform.common.bean.BaseBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: ketqi
 * Date: 2015-02-09 14:43
 */
public abstract class DefaultAbstractCache<T extends BaseBean> extends AbstractCache<T> {
    protected ConcurrentHashMap<Serializable, T> cache = new ConcurrentHashMap<>();

    @Override
    public abstract void updateReplicatorCache(boolean notifyCacheReplicators, Serializable... keys);

    @Override
    public void updateCache(boolean notifyCacheReplicators, List<T> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            Serializable[] keys = new Serializable[size];
            for (int i = 0; i < size; i++) {
                T item = list.get(i);
                if (item != null) {
                    keys[i] = item.getId();
                    cache.put(item.getId(), item);
                }
            }

            if (notifyCacheReplicators) {
                CacheManager.getInstance().addNotifyEvent(this, false, keys);
            }
        }
    }

    @Override
    public void removeCache(boolean notifyCacheReplicators, List<T> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            Serializable[] keys = new Serializable[size];
            for (int i = 0; i < size; i++) {
                T item = list.get(i);
                if (item != null) {
                    keys[i] = item.getId();
                    cache.remove(item.getId());
                }
            }

            if (notifyCacheReplicators) {
                CacheManager.getInstance().addNotifyEvent(this, true, keys);
            }
        }
    }

    /**
     * 缓存条目过滤
     *
     * @param condition 过滤条件
     * @return 过滤后的列表
     */
    @SuppressWarnings("unchecked")
    public <D extends CacheItemFilter> List<D> filterListTo(D condition) {
        List<D> list = new ArrayList<>();
        for (T item : cache.values()) {
            if (((CacheItemFilter) item).exist(condition)) {
                list.add((D) item);
            }
        }
        return list;
    }

    /**
     * 缓存条目过滤
     *
     * @param filter 过滤器
     * @return 过滤后的列表
     */
    public List<T> filterList(CacheItemFilter<T> filter) {
        List<T> list = new ArrayList<>();
        for (T item : cache.values()) {
            if (filter.exist(item)) {
                list.add(item);
            }
        }
        return list;
    }

    /**
     * 缓存条目过滤
     *
     * @param condition 过滤条件
     * @return 过滤后的条目
     */
    @SuppressWarnings("unchecked")
    public <D extends CacheItemFilter> D filterTo(D condition) {
        for (T item : cache.values()) {
            if (((CacheItemFilter) item).exist(condition)) {
                return (D) item;
            }
        }
        return null;
    }

    /**
     * 缓存条目过滤
     *
     * @param filter 过滤器
     * @return 过滤后的条目
     */
    public T filter(CacheItemFilter<T> filter) {
        for (T item : cache.values()) {
            if (filter.exist(item)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public T get(Serializable key) {
        return cache.get(key);
    }

    @Override
    public List<T> getAllList() {
        return new ArrayList<>(cache.values());
    }

    @Override
    public int getSize() {
        return cache.size();
    }
}
