/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 26, 2013
 */
package com.KyleDing.imcache.cache;

/**
 * The Interface CacheItem is a container for the cached values.
 *
 * @param <V> the value type
 */
public interface CacheItem<V> {

    /**
     * Gets the value.
     *
     * @return the value
     */
    V getValue();
}
