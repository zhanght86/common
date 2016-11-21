package com.sjdf.platform.cache;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Create at 2013年12月20日 上午9:28:52
 * 分布式缓存同步接口,内部使用,采用rmi方式
 *
 * @author KETQI
 */
public interface ReplicatorCache extends Remote, Serializable {
    /**
     * 缓存更新;管理器内部使用
     *
     * @param keys                   待更新的key列表
     * @param notifyCacheReplicators 是否通知缓存刷新
     * @param cacheClazzName         缓存对象全名称
     */
    void updateCache(String cacheClazzName, boolean notifyCacheReplicators, Serializable... keys) throws RemoteException;

    /**
     * 缓存数据删除;管理器内部使用
     *
     * @param keys                   待删除的key列表
     * @param notifyCacheReplicators 是否通知缓存刷新
     * @param cacheClazzName         缓存对象全名称
     */
    void removeCache(String cacheClazzName, boolean notifyCacheReplicators, Serializable... keys) throws RemoteException;
}
