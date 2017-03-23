/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Nov 15, 2013
 */
package com.KyleDing.imcache.examples;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.CacheType;
import com.KyleDing.imcache.cache.ImcacheType;
import com.KyleDing.imcache.cache.builder.CacheBuilder;
import com.KyleDing.imcache.cache.coordinator.CacheCoordinator;
import com.KyleDing.imcache.cache.coordinator.CacheFactory;
import com.KyleDing.imcache.cache.coordinator.LocalCacheCoordinator;

/**
 * The Class CacheCoordinatorExample.
 */
public class CacheCoordinatorExample {

    /** The str cache type. */
    private static CacheType<String, String> strCacheType = new ImcacheType<String, String>();

    /** The factory. */
    private static CacheFactory factory = new CacheFactory() {
        public <K, V> Cache<K, V> create() {
            return CacheBuilder.heapCache().build();
        }
    };

    public static void example() {
        final CacheCoordinator cacheCoordinator = new LocalCacheCoordinator(factory);
        final CacheTypeTest test = new CacheTypeTest(cacheCoordinator);
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    test.setValue(strCacheType, Thread.currentThread().getName(), Thread.currentThread().getName());
                    System.out.println(test.getValue(strCacheType, Thread.currentThread().getName()));
                }
            }, "Thread #" + i).start();
        }
    }

    /**
     * The Class CacheTypeTest.
     */
    private static class CacheTypeTest {

        /** The cache coordinator. */
        final CacheCoordinator cacheCoordinator;

        /**
         * Instantiates a new cache type test.
         *
         * @param cacheCoordinator the cache coordinator
         */
        public CacheTypeTest(CacheCoordinator cacheCoordinator) {
            this.cacheCoordinator = cacheCoordinator;
        }

        /**
         * Sets the value.
         *
         * @param type the type
         * @param key the key
         * @param value the value
         */
        public void setValue(CacheType<String, String> type, String key, String value) {
            cacheCoordinator.getCache(type).put(key, value);
        }

        /**
         * Gets the value.
         *
         * @param type the type
         * @param key the key
         * @return the value
         */
        public String getValue(CacheType<String, String> type, String key) {
            return cacheCoordinator.getCache(type).get(key);
        }
    }

    public static void main(String[] args) {
        example();
    }

}
