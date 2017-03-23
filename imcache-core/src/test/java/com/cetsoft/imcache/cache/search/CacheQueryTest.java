/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 4, 2015
 */
package com.KyleDing.imcache.cache.search;

import org.junit.Test;
import static org.junit.Assert.*;

import com.KyleDing.imcache.cache.search.criteria.Criteria;
import com.KyleDing.imcache.cache.search.criteria.ETCriteria;
import com.KyleDing.imcache.cache.search.filter.Filter;
import com.KyleDing.imcache.cache.search.filter.LTFilter;

public class CacheQueryTest {

    @Test
    public void cacheQuery() {
        Criteria etCriteria = new ETCriteria("id", 3);
        Filter ltFilter = new LTFilter("age", 18);
        Query query = CacheQuery.newQuery().setCriteria(etCriteria).setFilter(ltFilter);
        assertEquals(etCriteria, query.getCriteria());
        assertEquals(ltFilter, query.getFilter());
    }
}
