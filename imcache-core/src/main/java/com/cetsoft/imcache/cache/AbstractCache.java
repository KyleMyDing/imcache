/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 16, 2013
 */
package com.KyleDing.imcache.cache;

/**
 * The Class AbstractCache.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public abstract class AbstractCache<K, V> implements Cache<K, V> {

    /** The name. */
    private String name;

    /** The cache loader. */
    protected CacheLoader<K, V> cacheLoader;

    /** The eviction listener. */
    protected EvictionListener<K, V> evictionListener;

    /**
     * Instantiates a new abstract cache.
     *
     * @param cacheLoader the cache loader
     * @param evictionListener the eviction listener
     * @param indexHandler the query executer
     */
    public AbstractCache(CacheLoader<K, V> cacheLoader, EvictionListener<K, V> evictionListener) {
        this.cacheLoader = cacheLoader;
        this.evictionListener = evictionListener;
    }

    /**
     * Gets the cache loader.
     *
     * @return the cache loader
     */
    public CacheLoader<K, V> getCacheLoader() {
        return cacheLoader;
    }

    /**
     * Sets the cache loader.
     *
     * @param cacheLoader the cache loader
     */
    public void setCacheLoader(CacheLoader<K, V> cacheLoader) {
        this.cacheLoader = cacheLoader;
    }

    /**
     * Gets the eviction listener.
     *
     * @return the eviction listener
     */
    public EvictionListener<K, V> getEvictionListener() {
        return evictionListener;
    }

    /**
     * Sets the eviction listener.
     *
     * @param evictionListener the eviction listener
     */
    public void setEvictionListener(EvictionListener<K, V> evictionListener) {
        this.evictionListener = evictionListener;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.cache.Cache#getName()
     */
    public String getName() {
        if (name != null) {
            return name;
        }
        return this.getClass().getName();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.cache.Cache#setName(java.lang.String)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Calculates hit ratio.
     *
     * @param hit the hit
     * @param miss the miss
     * @return the double
     */
    protected double hitRatio(long hit, long miss) {
        long total = hit + miss;
        if (total == 0) {
            return 0;
        }
        return (double) hit / total;
    }

}
