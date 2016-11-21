package com.sjdf.platform.cache;

import com.sjdf.platform.common.bean.BaseBean;
import com.sjdf.platform.common.helper.ApplicationContextManager;
import com.sjdf.platform.common.service.BaseService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的分布式内存缓存实现
 * User: ketqi
 * Date: 2015-02-09 10:12
 */
public final class DefaultSimpleCache<T extends BaseBean> extends DefaultAbstractCache<T> {
    private Class<T> clazz;
    private static final ConcurrentHashMap<Class<?>, DefaultSimpleCache<?>> CLAZZ_CACHE = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T extends BaseBean> DefaultSimpleCache<T> getInstance(Class<T> clazz) {
        DefaultSimpleCache<T> cache = (DefaultSimpleCache<T>) CLAZZ_CACHE.get(clazz);
        if (cache != null) {
            return cache;
        }
        synchronized (CLAZZ_CACHE) {
            cache = (DefaultSimpleCache<T>) CLAZZ_CACHE.get(clazz);
            if (cache != null) {
                return cache;
            }
            cache = new DefaultSimpleCache<>(clazz);
            CLAZZ_CACHE.put(clazz, cache);
            return cache;
        }
    }

    private DefaultSimpleCache(Class<T> clazz) {
        this.clazz = clazz;
        CacheManager.getInstance().registerCache(this);
    }

    @Override
    public void updateReplicatorCache(boolean notifyCacheReplicators, Serializable... keys) {
        if (keys != null) {
            BaseService baseService = (BaseService) ApplicationContextManager.getBean("commonBaseService");
            List<T> list = new ArrayList<>(keys.length);
            for (Serializable key : keys) {
                T server = baseService.get(clazz, key);
                if (server != null) {
                    list.add(server);
                }
            }
            updateCache(false, list);
        }
    }

    @Override
    public String getName() {
        return clazz.getCanonicalName();
    }
}
