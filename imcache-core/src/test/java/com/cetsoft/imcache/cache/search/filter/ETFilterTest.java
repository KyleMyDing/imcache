/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jun 1, 2014
 */
package com.KyleDing.imcache.cache.search.filter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * The Class ETFilterTest.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ETFilterTest {

    /** The et filter. */
    ETFilter etFilter;

    /** The comparable. */
    @Mock
    Comparable comparable;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        etFilter = spy(new ETFilter("x", new Comparable() {
            public int compareTo(Object o) {
                return 0;
            }
        }));
    }

    /**
     * Filter.
     */
    @Test
    public void filter() {
        List<Object> objects = new ArrayList<Object>();
        objects.add(comparable);
        objects.add(comparable);
        doReturn(0).doReturn(1).when(comparable).compareTo(any());
        doReturn(comparable).when(etFilter).getAttributeValue(comparable);
        List<Object> actualObjects = etFilter.filter(objects);
        assertEquals(comparable, actualObjects.get(0));
    }

}
