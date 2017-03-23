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

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.CacheType;

/**
 * The Class LocalCacheCoordinatorTest.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LocalCacheCoordinatorTest {

    /** The cache. */
    @Mock
    Cache cache;

    /** The type. */
    @Mock
    CacheType type;

    /** The cache map. */
    @Mock
    Map<Integer, Cache> cacheMap;

    /** The cache factory. */
    @Mock
    CacheFactory cacheFactory;

    /** The cache coordinator. */
    @Spy
    LocalCacheCoordinator cacheCoordinator = new LocalCacheCoordinator();

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        cacheCoordinator.cacheFactory = cacheFactory;
    }

    /**
     * Gets the cache.
     *
     * @return the cache
     */
    @Test
    public void getCache() {
        doReturn(1).when(type).getType();
        doReturn(cacheMap).when(cacheCoordinator).getOrCreate();
        doReturn(cache).when(cacheMap).get(anyInt());
        Cache actualCache = cacheCoordinator.getCache(type);
        assertEquals(cache, actualCache);
    }

    /**
     * Gets the cache creates cache.
     *
     * @return the cache creates cache
     */
    @Test
    public void getCacheCreatesCache() {
        doReturn(1).when(type).getType();
        doReturn(cacheMap).when(cacheCoordinator).getOrCreate();
        doReturn(null).when(cacheMap).get(anyInt());
        doReturn(cache).when(cacheFactory).create();
        doNothing().when(cacheCoordinator).addCache(type, cache);
        Cache actualCache = cacheCoordinator.getCache(type);
        verify(cacheCoordinator).addCache(type, cache);
        assertEquals(cache, actualCache);
    }

    /**
     * Adds the cache.
     */
    @Test
    public void addCache() {
        doReturn(1).when(type).getType();
        doReturn(cacheMap).when(cacheCoordinator).getOrCreate();
        doReturn(null).when(cacheMap).put(1, cache);
        cacheCoordinator.addCache(type, cache);
        verify(cacheCoordinator).getOrCreate();
        verify(cacheMap).put(1, cache);
    }

    /**
     * Gets the or create.
     *
     * @return the or create
     */
    @Test
    public void getOrCreate() {
        Map<Integer, Cache> expectedMap = cacheCoordinator.getOrCreate();
        Map<Integer, Cache> actualMap = cacheCoordinator.getOrCreate();
        assertEquals(expectedMap, actualMap);
    }
}
