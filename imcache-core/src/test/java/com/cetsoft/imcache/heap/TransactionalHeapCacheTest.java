/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 21, 2014
 */
package com.KyleDing.imcache.heap;

import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.KyleDing.imcache.cache.CacheLoader;
import com.KyleDing.imcache.cache.EvictionListener;
import com.KyleDing.imcache.cache.search.IndexHandler;
import com.KyleDing.imcache.heap.TransactionalHeapCache;
import com.KyleDing.imcache.heap.tx.CacheTransaction;
import com.KyleDing.imcache.heap.tx.TransactionCommitter;
import com.KyleDing.imcache.heap.tx.TransactionException;

/**
 * The Class TransactionalHeapCacheTest.
 */
public class TransactionalHeapCacheTest {

    /** The cache loader. */
    @Mock
    CacheLoader<Object, Object> cacheLoader;

    /** The eviction listener. */
    @Mock
    EvictionListener<Object, Object> evictionListener;

    /** The index handler. */
    @Mock
    IndexHandler<Object, Object> indexHandler;

    /** The committer. */
    @Mock
    TransactionCommitter<Object, Object> committer;

    /** The cache. */
    TransactionalHeapCache<Object, Object> cache;

    /** The map. */
    @Mock
    Map<Object, Object> map;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        cache = spy(new TransactionalHeapCache<Object, Object>(committer, cacheLoader, evictionListener, indexHandler,
                1000));
        cache.cache = map;
    }

    /**
     * Put success.
     */
    @Test
    public void putSuccess() {
        doReturn("1").when(cache.cache).get(1);
        doNothing().when(committer).doPut(any(), any());
        CacheTransaction.get().begin();
        try {
            cache.put(1, "2");
            CacheTransaction.get().commit();
        } catch (TransactionException exception) {
            CacheTransaction.get().rollback();
        } finally {
            CacheTransaction.get().close();
        }
        verify(committer).doPut(any(), any());
    }

    /**
     * Put fail.
     */
    @Test
    public void putFail() {
        doReturn("1").when(cache.cache).get(1);
        doThrow(new TransactionException(new Exception())).when(committer).doPut(any(), any());
        CacheTransaction.get().begin();
        try {
            cache.put(1, "2");
            CacheTransaction.get().commit();
        } catch (TransactionException exception) {
            CacheTransaction.get().rollback();
        } finally {
            CacheTransaction.get().close();
        }
        verify(cache.cache, times(2)).put(any(), any());
    }
}
