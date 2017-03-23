/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 15, 2013
 */
package com.KyleDing.imcache.cache;

/**
 * The listener interface for receiving eviction events. The class that is
 * interested in processing a eviction event implements this interface, when the
 * eviction event occurs, that object's onEviction method is invoked.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface EvictionListener<K, V> {

    /**
     * Called on eviction.
     *
     * @param key the key
     * @param value the value
     */
    void onEviction(K key, V value);
}
