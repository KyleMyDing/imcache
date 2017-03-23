/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 23, 2013
 */
package com.KyleDing.imcache.cache.coordinator;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.CacheType;

/**
 * The Interface CacheCoordinator provides coordination of the various Cache
 * types. Each type of the cache is stored in CacheCoordinator and can be
 * retrieved from it whenever it is needed.
 */
public interface CacheCoordinator {

    /**
     * Gets the cache associated with type.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param type the type
     * @return the cache
     */
    <K, V> Cache<K, V> getCache(CacheType<K, V> type);

    /**
     * Adds the cache associated with type.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param type the type
     * @param cache the cache
     */
    <K, V> void addCache(CacheType<K, V> type, Cache<K, V> cache);
}
