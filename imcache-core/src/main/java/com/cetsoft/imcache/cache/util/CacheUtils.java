/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jan 4, 2014
 */
package com.KyleDing.imcache.cache.util;

import com.KyleDing.imcache.cache.CacheEntry;

/**
 * The Class CacheUtils.
 */
public class CacheUtils {

    /**
     * Creates the entry.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param key the key
     * @param value the value
     * @return the cache entry
     */
    public static <K, V> CacheEntry<K, V> createEntry(K key, V value) {
        return new DefaultCacheEntry<K, V>(key, value);
    }

    /**
     * The Class DefaultCacheEntry.
     *
     * @param <K> the key type
     * @param <V> the value type
     */
    protected static class DefaultCacheEntry<K, V> implements CacheEntry<K, V> {

        /** The key. */
        private K key;

        /** The value. */
        private V value;

        /**
         * Instantiates a new default cache entry.
         *
         * @param key the key
         * @param value the value
         */
        public DefaultCacheEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /*
         * (non-Javadoc)
         *
         * @see com.KyleDing.imcache.cache.CacheEntry#getKey()
         */
        public K getKey() {
            return key;
        }

        /*
         * (non-Javadoc)
         *
         * @see com.KyleDing.imcache.cache.CacheEntry#getValue()
         */
        public V getValue() {
            return value;
        }

    }
}
