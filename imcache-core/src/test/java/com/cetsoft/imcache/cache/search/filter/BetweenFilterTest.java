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
 * The Class BetweenFilterTest.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BetweenFilterTest {

    /** The between filter. */
    BetweenFilter betweenFilter;

    /** The upper bound. */
    @Mock
    Comparable comparable, lowerBound, upperBound;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        betweenFilter = spy(new BetweenFilter("x", lowerBound, upperBound));
    }

    /**
     * Filter.
     */
    @Test
    public void filter() {
        List<Object> objects = new ArrayList<Object>();
        objects.add(comparable);
        objects.add(comparable);
        doReturn(-1).when(comparable).compareTo(lowerBound);
        doReturn(1).when(comparable).compareTo(upperBound);
        doReturn(comparable).when(betweenFilter).getAttributeValue(comparable);
        List<Object> actualObjects = betweenFilter.filter(objects);
        assertEquals(comparable, actualObjects.get(0));
    }

}
