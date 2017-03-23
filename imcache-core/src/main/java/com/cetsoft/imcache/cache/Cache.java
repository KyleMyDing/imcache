/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 15, 2013
 */
package com.KyleDing.imcache.cache;

/**
 * The Interface Cache.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface Cache<K, V> {

    /**
     * Puts the value with the specified key.
     *
     * @param key the key
     * @param value the value
     */
    void put(K key, V value);

    /**
     * Gets the value with the specified key.
     *
     * @param key the key
     * @return the value
     */
    V get(K key);

    /**
     * Invalidate the value with the specified key.
     *
     * @param key the key
     * @return the value
     */
    V invalidate(K key);

    /**
     * Check if Cache contains the specified key.
     *
     * @param key the key
     * @return true, if successful
     */
    boolean contains(K key);

    /**
     * Clear the cache.
     */
    void clear();

    /**
     * Calculates the Hit ratio.
     *
     * @return the double
     */
    double hitRatio();

    /**
     * Gets the specified name if exist, otherwise returns the class name.
     *
     * @return the name
     */
    String getName();

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    void setName(String name);

    /**
     * Returns the number of elements in this cache.
     *
     * @return the number of elements
     */
    int size();
}
