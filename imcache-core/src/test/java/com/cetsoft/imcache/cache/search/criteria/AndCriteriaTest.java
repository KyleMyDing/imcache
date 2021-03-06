/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jun 2, 2014
 */
package com.KyleDing.imcache.cache.search.criteria;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.KyleDing.imcache.cache.search.index.CacheIndex;

/**
 * The Class AndCriteriaTest.
 */
public class AndCriteriaTest {

    /** The criteria. */
    @Mock
    Criteria criteria;

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
        AndCriteria andCriteria = new AndCriteria(criteria, criteria);
        Criteria[] criterias = andCriteria.getCriterias();
        assertEquals(criteria, criterias[0]);
    }

}
