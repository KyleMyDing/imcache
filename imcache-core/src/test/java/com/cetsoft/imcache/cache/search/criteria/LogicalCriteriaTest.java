/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 4, 2015
 */
package com.KyleDing.imcache.cache.search.criteria;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mock;

public class LogicalCriteriaTest {

    @Mock
    Criteria criteria;

    @Test
    public void logicalCriteria() {
        BetweenCriteria betweenCriteria = new BetweenCriteria("value", 3, 10);
        assertTrue(betweenCriteria.or(criteria) instanceof OrCriteria);
        assertTrue(betweenCriteria.and(criteria) instanceof AndCriteria);
        assertTrue(betweenCriteria.diff(criteria) instanceof DiffCriteria);
    }
}
