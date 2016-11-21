package com.sjdf.platform.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Create at 2013年8月8日 下午5:13:11
 * 键值不可被删除的映射,这是为了保证键不被其他或子类修改
 *
 * @param <K>
 * @param <V>
 * @author KETQI
 */
public class UnRemovableMap<K, V> extends HashMap<K, V> {

    private static final long serialVersionUID = 1L;

    public UnRemovableMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public UnRemovableMap(int initialCapacity) {
        super(initialCapacity);
    }

    public UnRemovableMap() {
    }

    public UnRemovableMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException("can not remove from it");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("can not clear");
    }
}
