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
 * The Class LazyCachePopulator populates the cache slowly after it is called.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public abstract class LazyCachePopulator<K, V> extends AbstractCachePopulator<K, V> {

    /**
     * Instantiates a new lazy cache populator.
     *
     * @param cache the cache
     */
    public LazyCachePopulator(Cache<K, V> cache) {
        super(cache);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.cache.CachePopulator#pupulate()
     */
    public void pupulate() {
        new Thread(new Runnable() {
            public void run() {
                List<CacheEntry<K, V>> entries = loadEntries();
                for (CacheEntry<K, V> cacheEntry : entries) {
                    cache.put(cacheEntry.getKey(), cacheEntry.getValue());
                }
            }
        }, "imcache:cachePopulator(name=" + cache.getName() + ",thread=0)").start();
    }

}
