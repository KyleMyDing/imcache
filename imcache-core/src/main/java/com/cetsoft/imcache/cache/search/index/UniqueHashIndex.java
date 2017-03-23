/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 29, 2013
 */
package com.KyleDing.imcache.cache.search.index;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The Class UniqueHashIndex is type of index where indexed value can have
 * exactly one corresponding value.
 */
public class UniqueHashIndex extends CacheIndexBase {

    /** The map. */
    protected Map<Object, Object> map = new ConcurrentHashMap<Object, Object>();

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.index.CacheIndex#put(java.lang.Object,
     * java.lang.Object)
     */
    public void put(Object indexedKey, Object key) {
        map.put(indexedKey, key);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.index.CacheIndex#remove(java.lang.Object
     * , java.lang.Object)
     */
    public void remove(Object indexedKey, Object key) {
        map.remove(indexedKey);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.index.CacheIndex#equalsTo(java.lang.
     * Object)
     */
    public List<Object> equalsTo(Object expectedValue) {
        List<Object> keys = new ArrayList<Object>(1);
        Object indexedKey = map.get(expectedValue);
        keys.add(indexedKey);
        return keys;
    }

}
