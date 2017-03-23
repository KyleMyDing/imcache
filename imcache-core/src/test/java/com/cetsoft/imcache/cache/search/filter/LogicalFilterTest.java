/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 4, 2015
 */
package com.KyleDing.imcache.cache.search.filter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mock;

public class LogicalFilterTest {

    @Mock
    Filter filter;

    @Test
    public void logicalFilter() {
        BetweenFilter betweenFilter = new BetweenFilter("value", 3, 10);
        assertTrue(betweenFilter.or(filter) instanceof OrFilter);
        assertTrue(betweenFilter.and(filter) instanceof AndFilter);
        assertTrue(betweenFilter.diff(filter) instanceof DiffFilter);
    }
}
