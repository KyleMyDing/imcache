/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 18, 2015
 */
package com.KyleDing.imcache.cache;

import java.util.ArrayList;
import java.util.List;

import com.KyleDing.imcache.cache.search.IndexHandler;
import com.KyleDing.imcache.cache.search.Query;

/**
 * The Class AbstractSearchableCache.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public abstract class AbstractSearchableCache<K, V> extends AbstractCache<K, V> implements SearchableCache<K, V> {

    /** The index handler. */
    protected IndexHandler<K, V> indexHandler;

    /**
     * Instantiates a new abstract searchable cache.
     *
     * @param cacheLoader the cache loader
     * @param evictionListener the eviction listener
     * @param indexHandler the index handler
     */
    public AbstractSearchableCache(CacheLoader<K, V> cacheLoader, EvictionListener<K, V> evictionListener,
            IndexHandler<K, V> indexHandler) {
        super(cacheLoader, evictionListener);
        this.indexHandler = indexHandler;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.SearchableCache#get(com.KyleDing.imcache.cache
     * .search.Query)
     */
    @SuppressWarnings("unchecked")
    public List<V> execute(Query query) {
        List<K> keys = indexHandler.execute(query);
        List<V> values = new ArrayList<V>(keys.size());
        for (K key : keys) {
            V value = get(key);
            if (value != null) {
                values.add(value);
            }
        }
        if (query.getFilter() != null) {
            values = (List<V>) query.getFilter().filter((List<Object>) values);
        }
        return values;
    }
}
