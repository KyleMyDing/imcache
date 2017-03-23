/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 28, 2013
 */
package com.KyleDing.imcache.cache;

import java.util.List;

import com.KyleDing.imcache.cache.search.Query;

/**
 * The Interface SearchableCache provides query execution for the caches.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface SearchableCache<K, V> extends Cache<K, V> {

    /**
     * Gets the list of items as a result of query execution.
     *
     * @param query the query
     * @return the list
     */
    List<V> execute(Query query);
}
