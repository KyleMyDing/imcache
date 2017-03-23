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
import com.KyleDing.imcache.cache.search.DefaultIndexHandler;
import com.KyleDing.imcache.cache.search.IndexHandler;
import com.KyleDing.imcache.cache.search.index.IndexType;
import com.KyleDing.imcache.heap.HeapCache;

/**
 * The Class HeapCacheBuilder.
 */
public class HeapCacheBuilder extends SearchableCacheBuilder {

    /** The capacity. */
    private int capacity = 10000;

    /**
     * Instantiates a new heap cache builder.
     */
    public HeapCacheBuilder() {
        super();
    }

    /**
     * Cache loader.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param cacheLoader the cache loader
     * @return the heap cache builder
     */
    @SuppressWarnings("unchecked")
    public <K, V> HeapCacheBuilder cacheLoader(CacheLoader<K, V> cacheLoader) {
        this.cacheLoader = (CacheLoader<Object, Object>) cacheLoader;
        return this;
    }

    /**
     * Eviction listener.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param evictionListener the eviction listener
     * @return the heap cache builder
     */
    @SuppressWarnings("unchecked")
    public <K, V> HeapCacheBuilder evictionListener(EvictionListener<K, V> evictionListener) {
        this.evictionListener = (EvictionListener<Object, Object>) evictionListener;
        return this;
    }

    /**
     * Query executer.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param indexHandler the query executer
     * @return the heap cache builder
     */
    @SuppressWarnings("unchecked")
    public <K, V> HeapCacheBuilder indexHandler(IndexHandler<K, V> indexHandler) {
        this.indexHandler = (IndexHandler<Object, Object>) indexHandler;
        isSearchable = true;
        return this;
    }

    /**
     * Capacity.
     *
     * @param capacity the capacity
     * @return the heap cache builder
     */
    public HeapCacheBuilder capacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    /**
     * Adds the index.
     *
     * @param attributeName the attribute name
     * @param indexType the index type
     * @return the heap cache builder
     */
    public HeapCacheBuilder addIndex(String attributeName, IndexType indexType) {
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
        return new HeapCache<K, V>((CacheLoader<K, V>) cacheLoader, (EvictionListener<K, V>) evictionListener,
                (IndexHandler<K, V>) indexHandler, capacity);
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

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.cache.builder.CacheBuilder#searchable()
     */
    @Override
    public void searchable() {
        if (!isSearchable) {
            indexHandler = new DefaultIndexHandler<Object, Object>();
            isSearchable = true;
        }
    }
}