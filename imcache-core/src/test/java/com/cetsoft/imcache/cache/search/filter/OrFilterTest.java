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
 * The Class OrFilterTest.
 */
@SuppressWarnings("unchecked")
public class OrFilterTest {

    /** The or filter. */
    OrFilter orFilter;

    /** The filter. */
    @Mock
    Filter filter;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        orFilter = spy(new OrFilter(filter, filter));
    }

    /**
     * Filter.
     */
    @Test
    public void filter() {
        Object object1 = new Object();
        Object object2 = new Object();
        Object object3 = new Object();
        List<Object> list1 = new ArrayList<Object>();
        list1.add(object1);
        list1.add(object2);
        List<Object> list2 = new ArrayList<Object>();
        list2.add(object2);
        list2.add(object3);
        doReturn(list1).doReturn(list2).when(filter).filter(anyList());
        List<Object> actualObjects = orFilter.filter(list1);
        assertTrue(actualObjects.contains(object1));
        assertTrue(actualObjects.contains(object2));
        assertTrue(actualObjects.contains(object3));
    }

}
