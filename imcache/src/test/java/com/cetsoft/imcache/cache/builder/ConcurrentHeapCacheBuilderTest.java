/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 3, 2015
 */
package com.KyleDing.imcache.cache.builder;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.SearchableCache;
import com.KyleDing.imcache.cache.search.index.IndexType;
import com.KyleDing.imcache.heap.ConcurrentHeapCache;

public class ConcurrentHeapCacheBuilderTest {

    @Test
    public void build() {
        Cache<Object, Object> cache = CacheBuilder.concurrentHeapCache().cacheLoader(AbstractCacheBuilder.CACHE_LOADER)
                .evictionListener(AbstractCacheBuilder.EVICTION_LISTENER).indexHandler(DummyIndexHandler.getInstance())
                .addIndex("age", IndexType.RANGE_INDEX).capacity(1000).build();
        assertTrue(cache instanceof SearchableCache);
        assertTrue(cache instanceof ConcurrentHeapCache);
    }
}
