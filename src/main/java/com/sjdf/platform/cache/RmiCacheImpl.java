package com.sjdf.platform.cache;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Create at 2013年12月19日 下午3:17:22
 * 分布式缓存接口实现,采用rmi实现,内部使用
 *
 * @author KETQI
 */
public class RmiCacheImpl extends UnicastRemoteObject implements ReplicatorCache {
    private static final long serialVersionUID = 7814152537497682630L;

    public RmiCacheImpl() throws RemoteException {
        super();
    }

    public void updateCache(String cacheClazzName, boolean notifyCacheReplicators, Serializable... keys) throws RemoteException {
        Cache<?> cache = CacheManager.getInstance().getCache(cacheClazzName);
        if (cache != null) {
            cache.updateReplicatorCache(false, keys);
        }
    }

    public void removeCache(String cacheClazzName, boolean notifyCacheReplicators, Serializable... keys) throws RemoteException {
        Cache<?> cache = CacheManager.getInstance().getCache(cacheClazzName);
        if (cache != null) {
            cache.removeReplicatorCache(false, keys);
        }
    }
}
