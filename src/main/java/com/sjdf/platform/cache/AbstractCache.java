package com.sjdf.platform.cache;

import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * 使用案例:
 * public final class PriceCache extends AbstractCache<PubPriceBean>{
 *     //选择实现缓存抽象方法
 *     public updateReplicatorCache(boolean notifyCacheReplicators, Serializable... keys);
 *
 *     //在更新或者删除时,请务必添加监听事件
 *     if (notifyCacheReplicators) {
 *         CacheManager.getInstance().addNotifyEvent(this, false, keys);
 *     }
 * }
 * </pre>
 * <p/>
 * Create at 2013年12月18日 下午3:12:43
 * <p/>
 * 支持分布式内存缓存,永不过期
 *
 * @param <T> 缓存对象类型
 * @author KETQI
 */
public abstract class AbstractCache<T> implements Cache<T> {
    protected final SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(getClass());

    /**
     * 缓存更新;管理器内部使用
     *
     * @param keys                   待更新的key列表
     * @param notifyCacheReplicators 是否通知缓存刷新
     */
    @Override
    public abstract void updateReplicatorCache(boolean notifyCacheReplicators, Serializable... keys);

    /**
     * 更新缓存
     *
     * @param notifyCacheReplicators 是否同步缓存
     * @param list                   待更新的缓存数据列表
     */
    @Override
    public abstract void updateCache(boolean notifyCacheReplicators, List<T> list);

    /**
     * 删除缓存数据
     *
     * @param notifyCacheReplicators 是否同步删除缓存数据
     * @param list                   待删除的缓存数据列表
     */
    @Override
    public abstract void removeCache(boolean notifyCacheReplicators, List<T> list);

    /**
     * 根据key获取缓存数据
     *
     * @param key 主键
     * @return 缓存数据
     */
    @Override
    public abstract T get(Serializable key);

    /**
     * 根据key获取缓存中的拷贝数据。如果对该数据有更新，需要调用 updateCache(T t)方法更新缓存
     *
     * @param key
     * @return 拷贝的数据
     */
    @SuppressWarnings("unchecked")
    @Override
    public T getCopyBean(Serializable key) {
        T t = get(key);
        if (t != null) {
            Object copy;
            try {
                copy = t.getClass().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("根据key获取缓存中的拷贝数据时出错", e);
            }
            BeanUtils.copyProperties(t, copy);
            return (T) copy;
        }
        return null;
    }

    /**
     * 获取缓存名称
     *
     * @return 名称
     */
    @Override
    public String getName() {
        return getClass().getCanonicalName();
    }

    /**
     * 获取缓存条目总数
     *
     * @return 总数
     */
    @Override
    public int getSize() {
        return 0;
    }

    /**
     * 缓存数据删除;管理器内部使用
     *
     * @param keys                   待删除的key列表
     * @param notifyCacheReplicators 是否通知缓存刷新
     */
    public void removeReplicatorCache(boolean notifyCacheReplicators, Serializable... keys) {
        if (keys != null) {
            logger.info(Arrays.toString(keys));
            List<T> list = new ArrayList<>(keys.length);
            for (Serializable key : keys) {
                T t = get(key);
                if (t != null) {
                    list.add(t);
                }
            }

            removeCache(false, list);
        }
    }

    /**
     * 初始化缓存
     *
     * @param list 待初始换的缓存数据列表
     */
    @Override
    public void init(List<T> list) {
        if (list != null && !list.isEmpty()) {
            updateCache(false, list);
        }
    }

    /**
     * 更新缓存
     *
     * @param t 待更新的缓存数据
     */
    @Override
    public void updateCache(T t) {
        if (t != null) {
            updateCache(true, t);
        }
    }

    /**
     * 更新缓存
     *
     * @param notifyCacheReplicators 是否同步更新缓存数据
     * @param t                      待更新的缓存数据
     */
    @Override
    public void updateCache(boolean notifyCacheReplicators, T t) {
        if (t != null) {
            updateCache(notifyCacheReplicators, Arrays.asList(t));
        }
    }

    /**
     * 更新缓存
     *
     * @param list 待更新的缓存数据列表
     */
    @Override
    public void updateCache(List<T> list) {
        if (list != null && !list.isEmpty()) {
            updateCache(true, list);
        }
    }

    /**
     * 删除缓存数据
     *
     * @param t 待删除的缓存数据
     */
    @Override
    public void removeCache(T t) {
        if (t != null) {
            removeCache(true, t);
        }
    }

    /**
     * 删除缓存数据
     *
     * @param notifyCacheReplicators 是否同步删除缓存数据
     * @param t                      待删除的数据
     */
    @Override
    public void removeCache(boolean notifyCacheReplicators, T t) {
        if (t != null) {
            removeCache(notifyCacheReplicators, Collections.singletonList(t));
        }
    }

    /**
     * 删除缓存数据
     *
     * @param list 待删除数据列表
     */
    @Override
    public void removeCache(List<T> list) {
        if (list != null && !list.isEmpty()) {
            removeCache(true, list);
        }
    }

    /**
     * 获取缓存中的所有条目
     *
     * @return 条目列表
     */
    @Override
    public List<T> getAllList() {
        return Collections.emptyList();
    }

    /**
     * 缓存条目过滤
     *
     * @param condition 过滤条件
     * @return 过滤后的列表
     */
    @Override
    public <D extends CacheItemFilter> List<D> filterListTo(D condition) {
        return Collections.emptyList();
    }

    /**
     * 缓存条目过滤
     *
     * @param filter 过滤器
     * @return 过滤后的列表
     */
    @Override
    public List<T> filterList(CacheItemFilter<T> filter) {
        return Collections.emptyList();
    }
}
