/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jan 4, 2014
 */
package com.KyleDing.imcache.cache.populator;

import java.util.List;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.CacheEntry;

/**
 * The Class SimpleCachePopulator populates the cache directly.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public abstract class SimpleCachePopulator<K, V> extends AbstractCachePopulator<K, V> {

    /**
     * Instantiates a new simple cache populator.
     *
     * @param cache the cache
     */
    public SimpleCachePopulator(Cache<K, V> cache) {
        super(cache);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.cache.CachePopulator#pupulate()
     */
    public void pupulate() {
        List<CacheEntry<K, V>> entries = loadEntries();
        for (CacheEntry<K, V> cacheEntry : entries) {
            cache.put(cacheEntry.getKey(), cacheEntry.getValue());
        }
    }

}
