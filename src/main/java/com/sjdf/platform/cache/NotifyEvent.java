package com.sjdf.platform.cache;

import com.sjdf.platform.CommonPlatformConstant;

import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 分布式缓存通知事件
 * Create at 2013年12月18日 下午4:55:04
 *
 * @author KETQI
 */
class NotifyEvent {
    /** 该事件所属缓存 */
    private String cacheClassName;
    /** 事件所针对的数据key */
    private Serializable[] keys;
    /** 事件针对的是删除或者添加更新 */
    private boolean isRemove;
    /** 失败通知次数 */
    private AtomicInteger counter = new AtomicInteger(0);
    /** 再次同步地址 */
    private String againSynAddress;

    public NotifyEvent() {
    }

    public NotifyEvent(Cache<?> cache, boolean isRemove, Serializable... keys) {
        this(cache.getName(), isRemove, keys);
    }

    public NotifyEvent(String cacheClassName, boolean isRemove, Serializable[] keys) {
        this.cacheClassName = cacheClassName;
        this.keys = keys;
        this.isRemove = isRemove;
    }

    public String getCacheClassName() {
        return cacheClassName;
    }

    public void setCacheClassName(String cacheClassName) {
        this.cacheClassName = cacheClassName;
    }

    public Serializable[] getKeys() {
        return keys;
    }

    public void setKeys(Serializable[] keys) {
        this.keys = keys;
    }

    public boolean isRemove() {
        return isRemove;
    }

    public void setRemove(boolean isRemove) {
        this.isRemove = isRemove;
    }

    public AtomicInteger getCounter() {
        return counter;
    }

    public void setCounter(AtomicInteger counter) {
        this.counter = counter;
    }

    public String getAgainSynAddress() {
        return againSynAddress;
    }

    public NotifyEvent setAgainSynAddress(String againSynAddress) {
        this.againSynAddress = againSynAddress;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(CommonPlatformConstant.LENGTH_256);
        sb.append("NotifyEvent{");
        sb.append("cacheClassName='").append(cacheClassName).append('\'');
        sb.append(", keys=").append(Arrays.toString(keys));
        sb.append(", isRemove=").append(isRemove);
        sb.append(", counter=").append(counter);
        sb.append(", againSynAddress='").append(againSynAddress).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
