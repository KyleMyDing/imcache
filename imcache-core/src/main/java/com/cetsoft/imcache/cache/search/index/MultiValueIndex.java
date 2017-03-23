/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Oct 26, 2013
 */
package com.KyleDing.imcache.cache.search.index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class MultiValueIndex is type of index where indexed value can have one
 * or more corresponding values.
 */
public abstract class MultiValueIndex extends CacheIndexBase {

    /** The lock. */
    private Lock lock = new ReentrantLock();

    /** The map. */
    protected Map<Object, Set<Object>> map;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.index.CacheIndex#put(java.lang.Object,
     * java.lang.Object)
     */
    public void put(Object indexedKey, Object key) {
        Set<Object> keyList = map.get(indexedKey);
        if (keyList == null) {
            lock.lock();
            try {
                keyList = map.get(indexedKey);
                if (keyList == null) {
                    keyList = new HashSet<Object>(3);
                    map.put(indexedKey, keyList);
                }
            } finally {
                lock.unlock();
            }
        }
        synchronized (keyList) {
            keyList.add(key);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.index.CacheIndex#remove(java.lang.Object
     * , java.lang.Object)
     */
    public void remove(Object indexedKey, Object key) {
        Set<Object> keyList = map.get(indexedKey);
        if (keyList == null) {
            return;
        }
        synchronized (keyList) {
            keyList.remove(key);
            if (keyList.size() == 0) {
                lock.lock();
                try {
                    map.remove(key);
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.index.CacheIndex#equalsTo(java.lang.
     * Object)
     */
    public List<Object> equalsTo(Object expectedValue) {
        Set<Object> result = map.get(expectedValue);
        if (result != null) {
            synchronized (result) {
                return new ArrayList<Object>(result);
            }
        }
        return Collections.emptyList();
    }
}
