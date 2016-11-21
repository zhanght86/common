package com.sjdf.platform.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Create at 2012-04-05
 * <p/>
 * 禁止删除的List
 *
 * @param <E>
 * @author 王正伟
 */
public class UnRemovableArrayList<E> extends ArrayList<E> {

    private static final long serialVersionUID = 1L;

    public UnRemovableArrayList() {
        this(Collections.<E>emptyList());
    }

    public UnRemovableArrayList(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("can not remove from it");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("can not remove from it");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("can not clear it");
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("can not removeRange from it");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("can not removeAll from it");
    }

}
