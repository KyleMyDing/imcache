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

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

/**
 * The Class ArithmeticFilterTest.
 */
public class ArithmeticFilterTest {

    /** The arithmetic filter. */
    ArithmeticFilter arithmeticFilter;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Gets the attribute value test.
     *
     * @return the attribute value test
     */
    @Test
    public void getAttributeValue() {
        arithmeticFilter = spy(new GTFilter("x", new Object()));
        int actualValue = (Integer) arithmeticFilter.getAttributeValue(new Runnable() {
            @SuppressWarnings("unused")
            int x = 10;

            public void run() {
            }
        });
        assertEquals(10, actualValue);
    }

}
