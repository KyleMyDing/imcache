/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 23, 2013
 */
package com.KyleDing.imcache.cache.coordinator;

import java.util.HashMap;
import java.util.Map;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.CacheType;

/**
 * The Class LocalCacheCoordinator provides local caching for the threads. This
 * coordinator can only be used for non-concurrent caching.
 */
@SuppressWarnings("rawtypes")
public class LocalCacheCoordinator implements CacheCoordinator {

    CacheFactory cacheFactory;

    public LocalCacheCoordinator() {
    }

    public LocalCacheCoordinator(CacheFactory cacheFactory) {
        this.cacheFactory = cacheFactory;
    }

    /** The cache map thread local. */
    ThreadLocal<Map<Integer, Cache>> cacheMapThreadLocal = new ThreadLocal<Map<Integer, Cache>>();

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.coordinator.CacheCoordinator#getCache(com.KyleDing
     * .imcache.cache.CacheType)
     */
    @SuppressWarnings("unchecked")
    public <K, V> Cache<K, V> getCache(CacheType<K, V> type) {
        Map<Integer, Cache> cacheMap = getOrCreate();
        Cache<K, V> cache = cacheMap.get(type.getType());
        if (cacheFactory != null && cache == null) {
            cache = cacheFactory.create();
            addCache(type, cache);
        }
        return cache;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.coordinator.CacheCoordinator#addCache(com.KyleDing
     * .imcache.cache.CacheType, com.KyleDing.imcache.cache.Cache)
     */
    public <K, V> void addCache(CacheType<K, V> type, Cache<K, V> cache) {
        Map<Integer, Cache> cacheMap = getOrCreate();
        cacheMap.put(type.getType(), cache);
    }

    protected Map<Integer, Cache> getOrCreate() {
        Map<Integer, Cache> cacheMap = cacheMapThreadLocal.get();
        if (cacheMap == null) {
            cacheMap = new HashMap<Integer, Cache>();
            cacheMapThreadLocal.set(new HashMap<Integer, Cache>());
        }
        return cacheMap;
    }
}
