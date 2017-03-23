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
 * The Class GTFilterTest.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GTFilterTest {

    /** The gt filter. */
    GTFilter gtFilter;

    /** The comparable. */
    @Mock
    Comparable comparable;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        gtFilter = spy(new GTFilter("x", new Comparable() {
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
        doReturn(comparable).when(gtFilter).getAttributeValue(comparable);
        List<Object> actualObjects = gtFilter.filter(objects);
        assertEquals(comparable, actualObjects.get(0));
    }

}
