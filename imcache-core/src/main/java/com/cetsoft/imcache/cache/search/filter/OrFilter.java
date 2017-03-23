/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Nov 8, 2013
 */
package com.KyleDing.imcache.cache.search.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Class OrFilter.
 */
public class OrFilter extends LogicalFilter {

    /** The filters. */
    private Filter[] filters;

    /**
     * Instantiates a new or filter.
     *
     * @param filters the filters
     */
    public OrFilter(Filter... filters) {
        this.filters = filters;
    }

    /**
     * Gets the filters.
     *
     * @return the filters
     */
    public Filter[] getFilters() {
        return filters;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.filter.Filter#filter(java.util.List)
     */
    public List<Object> filter(List<Object> objects) {
        Set<Object> results = new HashSet<Object>();
        for (Filter filter : filters) {
            List<Object> result = filter.filter(objects);
            results.addAll(result);
        }
        return new ArrayList<Object>(results);
    }
}
