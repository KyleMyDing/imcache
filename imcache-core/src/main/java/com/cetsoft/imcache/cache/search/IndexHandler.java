/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 28, 2013
 */
package com.KyleDing.imcache.cache.search;

import java.util.List;

/**
 * The Interface IndexHandler is for executing queries as well as adding and
 * removing data while making sure that they provide certain indexes.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface IndexHandler<K, V> extends Indexable {

    /**
     * Adds the key and value to be indexed.
     *
     * @param key the key
     * @param value the value
     */
    void add(K key, V value);

    /**
     * Removes the key and value to be indexed.
     *
     * @param key the key
     * @param value the value
     */
    void remove(K key, V value);

    /**
     * Clears the all the caches.
     */
    void clear();

    /**
     * Executes the given query.
     *
     * @param query the query
     * @return the list
     */
    List<K> execute(Query query);

}
