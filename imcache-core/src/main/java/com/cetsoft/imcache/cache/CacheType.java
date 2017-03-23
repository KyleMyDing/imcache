/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 15, 2013
 */
package com.KyleDing.imcache.cache;

/**
 * The Interface CacheType.
 *
 * @param <K> the key key
 * @param <V> the value value
 */
public interface CacheType<K, V> {

    /**
     * Gets the type of the cache.
     *
     * @return the type
     */
    int getType();
}
