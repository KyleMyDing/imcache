/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 28, 2013
 */
package com.KyleDing.imcache.cache.search;

import java.util.concurrent.ConcurrentHashMap;

import com.KyleDing.imcache.cache.search.index.CacheIndex;

/**
 * The Class ConcurrentIndexHandler handles the indexes for the caches in a
 * thread-safe manner.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class ConcurrentIndexHandler<K, V> extends DefaultIndexHandler<K, V> {

    /**
     * Instantiates a new concurrent query executer.
     */
    public ConcurrentIndexHandler() {
        indexes = new ConcurrentHashMap<String, CacheIndex>();
    }

}
