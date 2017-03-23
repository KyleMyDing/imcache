/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 25, 2014
 */
package com.KyleDing.imcache.cache.populator;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.CacheEntry;

/**
 * The Class LazyCachePopulatorTest.
 */
public class LazyCachePopulatorTest {

    /** The cache. */
    @Mock
    Cache<Object, Object> cache;

    /** The entry. */
    @Mock
    CacheEntry<Object, Object> entry;

    /** The populator. */
    LazyCachePopulator<Object, Object> populator;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        populator = spy(new LazyCachePopulator<Object, Object>(cache) {
            public List<CacheEntry<Object, Object>> loadEntries() {
                return null;
            }
        });
    }

    /**
     * Populate.
     */
    @Test
    public void populate() {
        Object object = new Object();
        List<CacheEntry<Object, Object>> entries = new ArrayList<CacheEntry<Object, Object>>();
        entries.add(entry);
        doReturn(object).when(entry).getKey();
        doReturn(object).when(entry).getValue();
        doReturn(entries).when(populator).loadEntries();
        doNothing().when(cache).put(object, object);
        populator.pupulate();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        verify(cache, atLeast(1)).put(object, object);
        verify(populator).loadEntries();
    }

}
