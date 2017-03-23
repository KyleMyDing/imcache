/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 26, 2013
 */
package com.KyleDing.imcache.cache;

/**
 * The Class SimpleItem is a basic implementation of a versionedItem.
 *
 * @param <V> the value type
 */
public class SimpleItem<V> implements VersionedItem<V> {

    /** The version. */
    private int version;

    /** The value. */
    private V value;

    /**
     * Instantiates a new simple cache item.
     *
     * @param value the value
     */
    public SimpleItem(V value) {
        this.value = value;
    }

    /**
     * Instantiates a new simple cache item.
     *
     * @param version the version
     * @param value the value
     */
    public SimpleItem(int version, V value) {
        this.version = version;
        this.value = value;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.cache.CacheItem#getValue()
     */
    public V getValue() {
        return value;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.cache.VersionedCacheItem#getVersion()
     */
    public int getVersion() {
        return version;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.cache.VersionedCacheItem#setVersion(int)
     */
    public int setVersion(int version) {
        this.version = version;
        return version;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.cache.VersionedItem#update(java.lang.Object)
     */
    public void update(V value) {
        this.value = value;
    }

}
