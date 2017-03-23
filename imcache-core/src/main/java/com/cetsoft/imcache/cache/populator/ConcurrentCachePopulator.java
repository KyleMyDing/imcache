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
 * The Class ConcurrentCachePopulator initializes threads to populate the cache.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public abstract class ConcurrentCachePopulator<K, V> extends AbstractCachePopulator<K, V> {

    /** The concurrency level. */
    private int concurrencyLevel;

    /** The Constant DEFAULT_CONCURRENCY_LEVEL. */
    private final static int DEFAULT_CONCURRENCY_LEVEL = 11;

    /**
     * Instantiates a new concurrent cache populator.
     *
     * @param cache the cache
     * @param concurrencyLevel the concurrency level
     */
    public ConcurrentCachePopulator(Cache<K, V> cache, int concurrencyLevel) {
        super(cache);
        this.concurrencyLevel = concurrencyLevel;
    }

    /**
     * Instantiates a new concurrent cache populator.
     *
     * @param cache the cache
     */
    public ConcurrentCachePopulator(Cache<K, V> cache) {
        this(cache, DEFAULT_CONCURRENCY_LEVEL);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.cache.CachePopulator#pupulate()
     */
    public void pupulate() {
        final List<CacheEntry<K, V>> entries = loadEntries();
        if (entries.size() < concurrencyLevel) {
            concurrencyLevel = entries.size();
        }
        final int partition = entries.size() / concurrencyLevel;
        for (int i = 0; i < concurrencyLevel; i++) {
            final int start = i * partition;
            final int stop = i != concurrencyLevel - 1 ? (i + 1) * partition : entries.size();
            new Thread(new Runnable() {
                public void run() {
                    for (int j = start; j < stop; j++) {
                        cache.put(entries.get(j).getKey(), entries.get(j).getValue());
                    }
                }
            }, "imcache:cachePopulator(name=" + cache.getName() + ",thread=" + i + ")").start();
        }
    }

}
