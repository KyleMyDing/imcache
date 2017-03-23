/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 4, 2015
 */
package com.KyleDing.imcache.spring;

import static org.junit.Assert.*;

import org.junit.Test;

import com.KyleDing.imcache.heap.ConcurrentHeapCache;
import com.KyleDing.imcache.heap.HeapCache;
import com.KyleDing.imcache.heap.TransactionalHeapCache;
import com.KyleDing.imcache.heap.tx.TransactionCommitter;
import com.KyleDing.imcache.offheap.OffHeapCache;
import com.KyleDing.imcache.offheap.VersionedOffHeapCache;
import com.KyleDing.imcache.offheap.bytebuffer.OffHeapByteBufferStore;

public class SpringCacheBuilderTest {

    @Test
    public void build() {
        SpringCacheBuilder builder = new SpringCacheBuilder();

        builder.setType("heap");
        assertTrue(builder.build() instanceof HeapCache);

        builder.setTransactionCommitter(new TransactionCommitter<Object, Object>() {
            @Override
            public void doPut(Object key, Object value) {
            }
        });
        builder.setType("transactionalheap");
        assertTrue(builder.build() instanceof TransactionalHeapCache);

        builder.setType("concurrentheap");
        builder.setConcurrencyLevel(2);
        assertTrue(builder.build() instanceof ConcurrentHeapCache);

        builder.setType("offheap");
        builder.setEvictionPeriod(2);
        builder.setBufferCleanerPeriod(1000);
        builder.setBufferCleanerThreshold(0.6F);
        OffHeapByteBufferStore bufferStore = new OffHeapByteBufferStore(8388608, 2);
        builder.setBufferStore(bufferStore);
        assertTrue(builder.build() instanceof OffHeapCache);

        builder.setType("versionedoffheap");
        assertTrue(builder.build() instanceof VersionedOffHeapCache);
    }
}
