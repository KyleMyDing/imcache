/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 15, 2013
 */
package com.KyleDing.imcache.cache;

/**
 * The CacheLoader interface for loading values with specified keys. The class
 * that is interested in loading values from a resource implements this
 * interface, and the object created with that class is registered with a
 * component. When the loading is requested, that object's appropriate method is
 * invoked.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface CacheLoader<K, V> {

    /**
     * Loads the value with specified key.
     *
     * @param key the key
     * @return the value
     */
    V load(K key);
}
