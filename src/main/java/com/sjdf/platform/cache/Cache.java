package com.sjdf.platform.cache;

import java.io.Serializable;
import java.util.List;

/**
 * Create at 2013年12月18日 下午3:12:43
 * 支持分布式内存缓存,永不过期
 *
 * @param <T> 缓存对象类型
 * @author KETQI
 */
public interface Cache<T> {
    /**
     * 获取缓存名称
     *
     * @return 名称
     */
    String getName();

    /**
     * 缓存更新;管理器内部使用
     *
     * @param keys                   待更新的key列表
     * @param notifyCacheReplicators 是否通知缓存刷新
     */
    void updateReplicatorCache(boolean notifyCacheReplicators, Serializable... keys);

    /**
     * 更新缓存
     *
     * @param notifyCacheReplicators 是否同步缓存
     * @param list                   待更新的缓存数据列表
     */
    void updateCache(boolean notifyCacheReplicators, List<T> list);

    /**
     * 更新缓存
     *
     * @param notifyCacheReplicators 是否同步更新缓存数据
     * @param t                      待更新的缓存数据
     */
    void updateCache(boolean notifyCacheReplicators, T t);

    /**
     * 更新缓存
     *
     * @param list 待更新的缓存数据列表
     */
    void updateCache(List<T> list);

    /**
     * 更新缓存
     *
     * @param t 待更新的缓存数据
     */
    void updateCache(T t);

    /**
     * 缓存数据删除;管理器内部使用
     *
     * @param keys                   待删除的key列表
     * @param notifyCacheReplicators 是否通知缓存刷新
     */
    void removeReplicatorCache(boolean notifyCacheReplicators, Serializable... keys);

    /**
     * 删除缓存数据
     *
     * @param notifyCacheReplicators 是否同步删除缓存数据
     * @param list                   待删除的缓存数据列表
     */
    void removeCache(boolean notifyCacheReplicators, List<T> list);

    /**
     * 删除缓存数据
     *
     * @param notifyCacheReplicators 是否同步删除缓存数据
     * @param t                      待删除的数据
     */
    void removeCache(boolean notifyCacheReplicators, T t);

    /**
     * 删除缓存数据
     *
     * @param list 待删除数据列表
     */
    void removeCache(List<T> list);

    /**
     * 删除缓存数据
     *
     * @param t 待删除的缓存数据
     */
    void removeCache(T t);

    /**
     * 根据key获取缓存数据
     *
     * @param key 主键
     * @return 缓存数据
     */
    T get(Serializable key);

    /**
     * 根据key获取缓存中的拷贝数据。如果对该数据有更新，需要调用 updateCache(T t)方法更新缓存
     *
     * @param key
     * @return 拷贝的数据
     */
    T getCopyBean(Serializable key);

    /**
     * 获取缓存条目总数
     *
     * @return 总数
     */
    int getSize();

    /**
     * 初始化缓存
     *
     * @param list 待初始换的缓存数据列表
     */
    void init(List<T> list);

    /**
     * 获取缓存中的所有条目
     *
     * @return 条目列表
     */
    List<T> getAllList();

    /**
     * 缓存条目过滤
     *
     * @param condition 过滤条件
     * @return 过滤后的列表
     */
    <D extends CacheItemFilter> List<D> filterListTo(D condition);

    /**
     * 缓存条目过滤
     *
     * @param filter 过滤器
     * @return 过滤后的列表
     */
    List<T> filterList(CacheItemFilter<T> filter);
}
