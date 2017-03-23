/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jan 4, 2014
 */
package com.KyleDing.imcache.cache;

/**
 * The Interface CacheEntry.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface CacheEntry<K, V> {

    /**
     * Gets the key.
     *
     * @return the key
     */
    K getKey();

    /**
     * Gets the value.
     *
     * @return the value
     */
    V getValue();
}
