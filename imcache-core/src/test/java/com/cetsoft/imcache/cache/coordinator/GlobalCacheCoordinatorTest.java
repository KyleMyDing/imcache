/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 25, 2014
 */
package com.KyleDing.imcache.cache.coordinator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.CacheType;

/**
 * The Class GlobalCacheCoordinatorTest.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GlobalCacheCoordinatorTest {

    /** The cache. */
    @Mock
    Cache cache;

    /** The cache type. */
    @Mock
    CacheType cacheType;

    /** The cache coordinator. */
    @Spy
    GlobalCacheCoordinator cacheCoordinator = new GlobalCacheCoordinator();

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Gets the cache.
     *
     * @return the cache
     */
    @Test
    public void getCache() {
        final CountDownLatch latch = new CountDownLatch(1);
        doReturn(1).when(cacheType).getType();
        new Thread(new Runnable() {
            public void run() {
                cacheCoordinator.addCache(cacheType, cache);
                latch.countDown();
            }
        }).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
        }
        Cache actualCache = cacheCoordinator.getCache(cacheType);
        assertEquals(cache, actualCache);
    }
}
