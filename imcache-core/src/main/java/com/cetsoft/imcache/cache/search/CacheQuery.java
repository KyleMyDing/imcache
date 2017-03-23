/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 29, 2013
 */
package com.KyleDing.imcache.cache.search;

import com.KyleDing.imcache.cache.search.criteria.Criteria;
import com.KyleDing.imcache.cache.search.filter.Filter;

/**
 * The Class CacheQuery is a base class for creating cache queries.
 */
public class CacheQuery implements Query {

    /** The criterias. */
    private Criteria criteria;

    private Filter filter;

    /**
     * Instantiates a new cache query.
     */
    private CacheQuery() {
    }

    /**
     * New instance.
     *
     * @return the query
     */
    public static Query newQuery() {
        return new CacheQuery();
    }

    public Query setCriteria(Criteria criteria) {
        this.criteria = criteria;
        return this;
    }

    public Criteria getCriteria() {
        return this.criteria;
    }

    public Query setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public Filter getFilter() {
        return this.filter;
    }

}
