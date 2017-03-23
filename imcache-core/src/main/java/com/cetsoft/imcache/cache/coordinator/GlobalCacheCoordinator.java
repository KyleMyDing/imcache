/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 23, 2013
 */
package com.KyleDing.imcache.cache.coordinator;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.CacheType;

/**
 * The Class GlobalCacheCoordinator provides global coordination for caches.
 * This coordinator can be used for concurrent accesses.
 */
public class GlobalCacheCoordinator implements CacheCoordinator {

    /** The lock. */
    private Lock lock = new ReentrantLock();

    /** The cache map. */
    @SuppressWarnings("rawtypes")
    ConcurrentMap<Integer, Cache> cacheMap = new ConcurrentHashMap<Integer, Cache>();

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.coordinator.CacheCoordinator#getCache(com.KyleDing
     * .imcache.cache.CacheType)
     */
    @SuppressWarnings("unchecked")
    public <K, V> Cache<K, V> getCache(CacheType<K, V> type) {
        return cacheMap.get(type.getType());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.coordinator.CacheCoordinator#addCache(com.KyleDing
     * .imcache.cache.CacheType, com.KyleDing.imcache.cache.Cache)
     */
    public <K, V> void addCache(CacheType<K, V> type, Cache<K, V> cache) {
        if (getCache(type) == null) {
            lock.lock();
            try {
                if (getCache(type) == null) {
                    cacheMap.put(type.getType(), cache);
                }
            } finally {
                lock.unlock();
            }
        }
    }

}
