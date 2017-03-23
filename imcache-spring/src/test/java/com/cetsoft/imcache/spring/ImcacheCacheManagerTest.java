/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 4, 2015
 */
package com.KyleDing.imcache.spring;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.KyleDing.imcache.cache.SearchableCache;
import com.KyleDing.imcache.cache.builder.AbstractCacheBuilder;

public class ImcacheCacheManagerTest {

    @Mock
    AbstractCacheBuilder builder;

    @Mock
    SearchableCache<Object, Object> cache;

    ImcacheCacheManager cacheManager;

    String cacheName = "cache";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        doReturn(cache).when(builder).build(cacheName);
        cacheManager = new ImcacheCacheManager(builder);
    }

    @Test
    public void getCache() {
        assertEquals(cache, cacheManager.getCache(cacheName).getNativeCache());
    }

    @Test
    public void getCacheNames() {
        cacheManager.getCache(cacheName);
        assertEquals(1, cacheManager.getCacheNames().size());
        assertTrue(cacheManager.getCacheNames().contains(cacheName));
    }

    @Test
    public void setGetCacheBuilder() {
        cacheManager = new ImcacheCacheManager();
        cacheManager.setCacheBuilder(builder);
        assertEquals(builder, cacheManager.getCacheBuilder());
    }

}
