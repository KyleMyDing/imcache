/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 23, 2013
 */
package com.KyleDing.imcache.cache.builder;

/**
 * The Class CacheBuilder.
 */
public abstract class CacheBuilder {

    /**
     * Heap cache.
     *
     * @return the heap cache builder
     */
    public static HeapCacheBuilder heapCache() {
        return new HeapCacheBuilder();
    }

    /**
     * Transactional Heap cache.
     *
     * @return the transactional heap cache builder
     */
    public static TransactionalHeapCacheBuilder transactionalHeapCache() {
        return new TransactionalHeapCacheBuilder();
    }

    /**
     * Concurrent heap cache.
     *
     * @return the concurrent heap cache builder
     */
    public static ConcurrentHeapCacheBuilder concurrentHeapCache() {
        return new ConcurrentHeapCacheBuilder();
    }

    /**
     * Off heap cache.
     *
     * @return the off heap cache builder
     */
    public static OffHeapCacheBuilder offHeapCache() {
        return new OffHeapCacheBuilder();
    }

    /**
     * Versioned Off heap cache.
     *
     * @return the off heap cache builder
     */
    public static VersionedOffHeapCacheBuilder versionedOffHeapCache() {
        return new VersionedOffHeapCacheBuilder();
    }

    /**
     * Redis cache.
     *
     * @return the redis cache builder
     */
    public static RedisCacheBuilder redisCache() {
        return new RedisCacheBuilder();
    }

}
