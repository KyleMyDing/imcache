/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 18, 2015
 */
package com.KyleDing.imcache.cache.builder;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.redis.RedisCache;

/**
 * The Class RedisCacheBuilderTest.
 */
public class RedisCacheBuilderTest {

    /**
     * Builds the.
     */
    @Test
    public void build() {
        Cache<Object, Object> cache = CacheBuilder.redisCache().cacheLoader(AbstractCacheBuilder.CACHE_LOADER)
                .evictionListener(AbstractCacheBuilder.EVICTION_LISTENER).serializer(AbstractCacheBuilder.SERIALIZER)
                .hostName("localhost").port(6379).concurrencyLevel(2).build();
        assertTrue(cache instanceof RedisCache);
    }
}
