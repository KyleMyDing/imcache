/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jan 6, 2014
 */
package com.KyleDing.imcache.cache.builder;

import com.KyleDing.imcache.cache.CacheLoader;
import com.KyleDing.imcache.cache.EvictionListener;
import com.KyleDing.imcache.cache.SearchableCache;
import com.KyleDing.imcache.cache.search.IndexHandler;
import com.KyleDing.imcache.cache.search.index.IndexType;
import com.KyleDing.imcache.heap.ConcurrentHeapCache;

/**
 * The Class ConcurrentHeapCacheBuilder.
 */
public class ConcurrentHeapCacheBuilder extends SearchableCacheBuilder {

    /** The capacity. */
    private int capacity = 10000;

    /**
     * Instantiates a new concurrent heap cache builder.
     */
    public ConcurrentHeapCacheBuilder() {
        super();
    }

    /**
     * Cache loader.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param cacheLoader the cache loader
     * @return the concurrent heap cache builder
     */
    @SuppressWarnings("unchecked")
    public <K, V> ConcurrentHeapCacheBuilder cacheLoader(CacheLoader<K, V> cacheLoader) {
        this.cacheLoader = (CacheLoader<Object, Object>) cacheLoader;
        return this;
    }

    /**
     * Eviction listener.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param evictionListener the eviction listener
     * @return the concurrent heap cache builder
     */
    @SuppressWarnings("unchecked")
    public <K, V> ConcurrentHeapCacheBuilder evictionListener(EvictionListener<K, V> evictionListener) {
        this.evictionListener = (EvictionListener<Object, Object>) evictionListener;
        return this;
    }

    /**
     * Query executer.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param indexHandler the query executer
     * @return the concurrent heap cache builder
     */
    @SuppressWarnings("unchecked")
    public <K, V> ConcurrentHeapCacheBuilder indexHandler(IndexHandler<K, V> indexHandler) {
        this.indexHandler = (IndexHandler<Object, Object>) indexHandler;
        isSearchable = true;
        return this;
    }

    /**
     * Capacity.
     *
     * @param capacity the capacity
     * @return the concurrent heap cache builder
     */
    public ConcurrentHeapCacheBuilder capacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    /**
     * Adds the index.
     *
     * @param attributeName the attribute name
     * @param indexType the index type
     * @return the concurrent heap cache builder
     */
    public ConcurrentHeapCacheBuilder addIndex(String attributeName, IndexType indexType) {
        searchable();
        indexHandler.addIndex(attributeName, indexType);
        return this;
    }

    /**
     * Builds the cache.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return the cache
     */
    @SuppressWarnings("unchecked")
    public <K, V> SearchableCache<K, V> build() {
        return new ConcurrentHeapCache<K, V>((CacheLoader<K, V>) cacheLoader,
                (EvictionListener<K, V>) evictionListener, (IndexHandler<K, V>) indexHandler, capacity);
    }

    /**
     * Builds the cache.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param cacheName the cache name
     * @return the searchable cache
     */
    public <K, V> SearchableCache<K, V> build(String cacheName) {
        SearchableCache<K, V> cache = build();
        cache.setName(cacheName);
        return cache;
    }
}
