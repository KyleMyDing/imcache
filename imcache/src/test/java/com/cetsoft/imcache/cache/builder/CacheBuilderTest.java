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

public class CacheBuilderTest {

    @Test
    public void heapCache() {
        assertTrue(CacheBuilder.heapCache() instanceof HeapCacheBuilder);
    }

    @Test
    public void transactionalHeapCache() {
        assertTrue(CacheBuilder.transactionalHeapCache() instanceof TransactionalHeapCacheBuilder);
    }

    @Test
    public void concurrentHeapCache() {
        assertTrue(CacheBuilder.concurrentHeapCache() instanceof ConcurrentHeapCacheBuilder);
    }

    @Test
    public void offHeapCache() {
        assertTrue(CacheBuilder.offHeapCache() instanceof OffHeapCacheBuilder);
    }

    @Test
    public void versionedOffHeapCache() {
        assertTrue(CacheBuilder.versionedOffHeapCache() instanceof VersionedOffHeapCacheBuilder);
    }

}
