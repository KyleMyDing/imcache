/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 28, 2013
 */
package com.KyleDing.imcache.cache.search;

import com.KyleDing.imcache.cache.search.criteria.Criteria;
import com.KyleDing.imcache.cache.search.filter.Filter;

/**
 * The Interface Query is a piece of code (a query) that is sent to a cache in
 * order to get information back from the cache. It is used as the way of
 * retrieving the information from cache.
 */
public interface Query {

    /**
     * Adds the criteria.
     *
     * @param criteria the criteria
     * @return the query
     */
    Query setCriteria(Criteria criteria);

    /**
     * Returns list of Criterias.
     *
     * @return the list
     */
    Criteria getCriteria();

    /**
     * Sets the filter.
     *
     * @param filter the filter
     * @return the query
     */
    Query setFilter(Filter filter);

    /**
     * Gets the filter.
     *
     * @return the filter
     */
    Filter getFilter();
}
