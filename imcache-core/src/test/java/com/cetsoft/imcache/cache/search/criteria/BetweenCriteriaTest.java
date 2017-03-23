/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jun 2, 2014
 */
package com.KyleDing.imcache.cache.search.criteria;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.KyleDing.imcache.cache.search.index.CacheIndex;

/**
 * The Class BetweenCriteriaTest.
 */
public class BetweenCriteriaTest {

    /** The upper bound. */
    @Mock
    Object lowerBound, upperBound;

    /** The result. */
    @Mock
    List<Object> result;

    /** The cache index. */
    @Mock
    CacheIndex cacheIndex;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Gets the criterias.
     *
     * @return the criterias
     */
    @Test
    public void getCriterias() {
        BetweenCriteria betweenCriteria = new BetweenCriteria("a", lowerBound, upperBound);
        doReturn(result).when(cacheIndex).between(lowerBound, upperBound);
        List<Object> actualResult = betweenCriteria.meets(cacheIndex);
        verify(cacheIndex).between(lowerBound, upperBound);
        assertEquals(result, actualResult);
    }

}
