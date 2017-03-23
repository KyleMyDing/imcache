/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 26, 2013
 */
package com.KyleDing.imcache.cache;

/**
 * The Interface VersionedItem provides versioning for the cached items.
 *
 * @param <V> the value type
 */
public interface VersionedItem<V> extends CacheItem<V> {

    /**
     * Gets the version.
     *
     * @return the version
     */
    int getVersion();

    /**
     * Sets the version.
     *
     * @param version the version
     * @return the int
     */
    int setVersion(int version);

    /**
     * Updates the current value.
     *
     * @param value the value
     */
    void update(V value);
}
