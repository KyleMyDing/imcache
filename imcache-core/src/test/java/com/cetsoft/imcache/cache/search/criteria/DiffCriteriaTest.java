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
 * The Class DiffCriteriaTest.
 */
public class DiffCriteriaTest {

    /** The right criteria. */
    @Mock
    Criteria leftCriteria, rightCriteria;

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
     * Left right criteria.
     */
    @Test
    public void leftRightCriteria() {
        DiffCriteria diffCriteria = new DiffCriteria(leftCriteria, rightCriteria);
        Criteria actualLeftCriteria = diffCriteria.getLeftCriteria();
        Criteria actualRightCriteria = diffCriteria.getRightCriteria();
        assertEquals(leftCriteria, actualLeftCriteria);
        assertEquals(rightCriteria, actualRightCriteria);
    }

}
