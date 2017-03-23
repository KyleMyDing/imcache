/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 25, 2014
 */
package com.KyleDing.imcache.cache.search.index;

import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.KyleDing.imcache.cache.search.index.CacheIndexBase;

/**
 * The Class CacheIndexBaseTest.
 */
public class CacheIndexBaseTest {

    /** The index base. */
    CacheIndexBase indexBase;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        indexBase = spy(new CacheIndexBase() {
            public void remove(Object indexedKey, Object key) {
            }

            public void put(Object indexedKey, Object key) {
            }
        });
    }

    /**
     * Equals to.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void equalsTo() {
        indexBase.equalsTo(new Object());
    }

    /**
     * Less than.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void lessThan() {
        indexBase.lessThan(new Object());
    }

    /**
     * Less than or equals to.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void lessThanOrEqualsTo() {
        indexBase.lessThanOrEqualsTo(new Object());
    }

    /**
     * Greater than.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void greaterThan() {
        indexBase.greaterThan(new Object());
    }

    /**
     * Greater than or equals to.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void greaterThanOrEqualsTo() {
        indexBase.greaterThanOrEqualsTo(new Object());
    }

    /**
     * Between.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void between() {
        indexBase.between(new Object(), new Object());
    }

}
