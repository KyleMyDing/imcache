/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 3, 2015
 */
package com.KyleDing.imcache.cache.builder;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.SearchableCache;
import com.KyleDing.imcache.cache.search.index.IndexType;
import com.KyleDing.imcache.offheap.VersionedOffHeapCache;
import com.KyleDing.imcache.offheap.bytebuffer.OffHeapByteBufferStore;

public class VersionedOffHeapCacheBuilderTest {

    @Test
    public void build() {
        OffHeapByteBufferStore bufferStore = new OffHeapByteBufferStore(8388608, 10);
        Cache<Object, Object> cache = CacheBuilder.versionedOffHeapCache().storage(bufferStore)
                .cacheLoader(AbstractCacheBuilder.CACHE_LOADER)
                .evictionListener(AbstractCacheBuilder.EVICTION_LISTENER).indexHandler(DummyIndexHandler.getInstance())
                .addIndex("age", IndexType.RANGE_INDEX).serializer(AbstractCacheBuilder.SERIALIZER)
                .bufferCleanerPeriod(100).bufferCleanerThreshold(0.6f).concurrencyLevel(10).evictionPeriod(100).build();
        assertTrue(cache instanceof SearchableCache);
        assertTrue(cache instanceof VersionedOffHeapCache);
    }

    @Test(expected = NecessaryArgumentException.class)
    public void buildThrowsNecessaryArgumentException() {
        CacheBuilder.transactionalHeapCache().build();
    }
}
