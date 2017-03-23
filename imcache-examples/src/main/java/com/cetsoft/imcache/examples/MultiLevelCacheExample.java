/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 20, 2014
 */
package com.KyleDing.imcache.examples;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.CacheLoader;
import com.KyleDing.imcache.cache.EvictionListener;
import com.KyleDing.imcache.cache.builder.CacheBuilder;
import com.KyleDing.imcache.offheap.bytebuffer.OffHeapByteBufferStore;

/**
 * The Class MultiLevelCacheExample.
 */
public class MultiLevelCacheExample {

    @SuppressWarnings("null")
    public static void example() {
        final CacheDao cacheDao = null;// This is just for example purposes.
        OffHeapByteBufferStore bufferStore = new OffHeapByteBufferStore(10000, 10);
        final Cache<String, String> offHeapCache = CacheBuilder.offHeapCache().storage(bufferStore)
                .cacheLoader(new CacheLoader<String, String>() {
                    public String load(String key) {
                        return cacheDao.load(key);
                    }
                }).evictionListener(new EvictionListener<String, String>() {
                    public void onEviction(String key, String value) {
                        cacheDao.store(key, value);
                    }
                }).build();
        Cache<String, String> multiLevelCache = CacheBuilder.heapCache().cacheLoader(new CacheLoader<String, String>() {
            public String load(String key) {
                return offHeapCache.get(key);
            }
        }).evictionListener(new EvictionListener<String, String>() {
            public void onEviction(String key, String value) {
                offHeapCache.put(key, value);
            }
        }).capacity(10000).build();
        multiLevelCache.put("red", "apple");
    }

    /**
     * The Interface CacheDao.
     */
    public static interface CacheDao {

        /**
         * Load.
         *
         * @param key the key
         * @return the string
         */
        String load(String key);

        /**
         * Store.
         *
         * @param key the key
         * @param value the value
         */
        void store(String key, String value);
    }

    public static void main(String[] args) {
        example();
    }
}
