/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 4, 2015
 */
package com.KyleDing.imcache.cache;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.KyleDing.imcache.cache.search.IndexHandler;
import com.KyleDing.imcache.heap.HeapCache;

public class AbstractCacheTest {

    @Mock
    IndexHandler<Integer, Item> indexHandler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void hitRatio() {
        AbstractCache<Integer, Item> abstractCache = new HeapCache<Integer, Item>(new CacheLoader<Integer, Item>() {
            public Item load(Integer key) {
                return null;
            }
        }, new EvictionListener<Integer, Item>() {
            public void onEviction(Integer key, Item value) {
            }
        }, indexHandler, 10);
        long hit = 2;
        long miss = 3;
        assertEquals(2 / 5.0, abstractCache.hitRatio(hit, miss), 0.000001);
    }

    @Test
    public void hitRatioWithZeroMissAndHit() {
        AbstractCache<Integer, Item> abstractCache = new HeapCache<Integer, Item>(new CacheLoader<Integer, Item>() {
            public Item load(Integer key) {
                return null;
            }
        }, new EvictionListener<Integer, Item>() {
            public void onEviction(Integer key, Item value) {
            }
        }, indexHandler, 10);
        assertEquals(0.0, abstractCache.hitRatio(0, 0), 0.000001);
    }

    private static class Item {
    }

}
