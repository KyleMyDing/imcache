/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 4, 2015
 */
package com.KyleDing.imcache.cache;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.KyleDing.imcache.cache.search.CacheQuery;
import com.KyleDing.imcache.cache.search.IndexHandler;
import com.KyleDing.imcache.cache.search.Query;
import com.KyleDing.imcache.cache.search.criteria.Criteria;
import com.KyleDing.imcache.cache.search.filter.Filter;
import com.KyleDing.imcache.heap.HeapCache;

public class AbstractSearchableCacheTest {

    @Mock
    Criteria criteria;

    @Mock
    Filter filter;

    @Mock
    IndexHandler<Integer, Item> indexHandler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void execute() {
        AbstractSearchableCache<Integer, Item> abstractCache = new HeapCache<Integer, Item>(
                new CacheLoader<Integer, Item>() {
                    public Item load(Integer key) {
                        return null;
                    }
                }, new EvictionListener<Integer, Item>() {
                    public void onEviction(Integer key, Item value) {
                    }
                }, indexHandler, 10);
        Item item1 = new Item(1);
        Item item2 = new Item(2);
        abstractCache.put(1, item1);
        abstractCache.put(2, item1);
        List<Integer> keys = new ArrayList<Integer>();
        keys.add(1);
        keys.add(2);
        List<Item> values = new ArrayList<Item>();
        values.add(item1);
        values.add(item2);
        doReturn(keys).when(indexHandler).execute(any(Query.class));
        doReturn(values).when(filter).filter(anyList());
        List<Item> items = abstractCache.execute(CacheQuery.newQuery().setCriteria(criteria).setFilter(filter));
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
    }

    private static class Item {
        @SuppressWarnings("unused")
        private int value;

        public Item(int value) {
            this.value = value;
        }
    }

}
