/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 25, 2014
 */
package com.KyleDing.imcache.cache.search.index;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class NonUniqueHashIndexTest {

    /** The index. */
    MultiValueIndex index;

    @Spy
    Map<Object, Set<Object>> map = new HashMap<Object, Set<Object>>();

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        index = spy(new NonUniqueHashIndex());
        index.map = map;
    }

    @Test
    public void put() {
        Object object = new Object();
        index.put(object, object);
        assertTrue(index.map.get(object).contains(object));
    }

    @Test
    public void remove() {
        Object object = new Object();
        index.remove(object, object);
        verify(map, times(0)).remove(object);
    }

    @Test
    public void removeFromBothMapAndSet() {
        Object object = new Object();
        Set<Object> set = spy(new HashSet<Object>());
        map.put(object, set);
        map.get(object).add(object);
        index.remove(object, object);
        verify(set).remove(object);
        verify(map).remove(object);
    }

    @Test
    public void equalsToEmptyResult() {
        Object object = new Object();
        List<Object> result = index.equalsTo(object);
        assertTrue(result.size() == 0);
    }

    @Test
    public void equalsTo() {
        Object object = new Object();
        Set<Object> set = spy(new HashSet<Object>());
        map.put(object, set);
        set.add(object);
        List<Object> result = index.equalsTo(object);
        assertEquals(object, result.get(0));
    }

}
