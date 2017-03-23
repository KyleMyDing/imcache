/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jan 4, 2014
 */
package com.KyleDing.imcache.cache.populator;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.CachePopulator;

/**
 * The Class AbstractCachePopulator populates the cache.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public abstract class AbstractCachePopulator<K, V> implements CachePopulator<K, V> {

    /** The cache. */
    protected Cache<K, V> cache;

    /**
     * Instantiates a new abstract cache populator.
     *
     * @param cache the cache
     */
    public AbstractCachePopulator(Cache<K, V> cache) {
        this.cache = cache;
    }
}
