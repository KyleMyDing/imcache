/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 20, 2014
 */
package com.KyleDing.imcache.cache.coordinator;

import com.KyleDing.imcache.cache.Cache;

/**
 * A factory for creating Cache objects.
 */
public interface CacheFactory {

    /**
     * Creates the.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return the cache
     */
    <K, V> Cache<K, V> create();
}