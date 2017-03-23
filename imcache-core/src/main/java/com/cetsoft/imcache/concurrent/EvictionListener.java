/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 2, 2014
 */
package com.KyleDing.imcache.concurrent;

/**
 * The listener interface for receiving eviction events. When the eviction event
 * occurs, that object's onEviction method is invoked.
 *
 * @param <K> the key type
 * @param <V> the value type
 * @see EvictionEvent
 */
public interface EvictionListener<K, V> {

    /**
     * On eviction.
     *
     * @param key the key
     * @param value the value
     */
    void onEviction(K key, V value);
}
