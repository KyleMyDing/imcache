/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Christian Bourque
 * Date   : August 1, 2015
 */
package com.KyleDing.imcache.cache.coordinator;

import com.KyleDing.imcache.cache.Cache;

/**
 * The singleton cache coordinator is an extension of the global cache
 * coordinator. As the name indicates it's a singleton that can be handy when
 * you need a single cache coordinator for a whole application which can be
 * accessible from anywhere.
 */
public final class SingletonCacheCoordinator extends GlobalCacheCoordinator {

    private volatile static SingletonCacheCoordinator instance;

    private SingletonCacheCoordinator() {
    }

    /**
     * Get sole instance of this class.
     *
     * @return the singleton instance
     */
    public static SingletonCacheCoordinator getInstance() {
        if (instance == null) {
            synchronized (SingletonCacheCoordinator.class) {
                if (instance == null) {
                    instance = new SingletonCacheCoordinator();
                }
            }
        }
        return instance;
    }

    /**
     * Clear all caches.
     */
    @SuppressWarnings("rawtypes")
    public synchronized void clearAll() {
        for (Cache cache : cacheMap.values()) {
            cache.clear();
        }
    }

}
