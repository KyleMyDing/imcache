/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jun 5, 2014
 */
package com.KyleDing.imcache.cache.async;

/**
 * The Interface CacheTask.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface CacheTask<K, V> {

    /**
     * Gets the value.
     *
     * @return the value
     */
    V getValue();

    /**
     * Gets the key.
     *
     * @return the key
     */
    K getKey();
}
