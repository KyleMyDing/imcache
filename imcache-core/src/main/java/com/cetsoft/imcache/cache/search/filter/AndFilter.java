/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Nov 8, 2013
 */
package com.KyleDing.imcache.cache.search.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class AndFilter.
 */
public class AndFilter extends LogicalFilter {

    /** The filters. */
    private Filter[] filters;

    /**
     * Instantiates a new and filter.
     *
     * @param filters the filters
     */
    public AndFilter(Filter... filters) {
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
        List<Object> results = new ArrayList<Object>();
        for (Filter filter : filters) {
            List<Object> result = filter.filter(objects);
            if (results.size() == 0) {
                results.addAll(result);
            } else {
                List<Object> mergedResults = new ArrayList<Object>(results.size());
                for (Object object : result) {
                    if (results.contains(object)) {
                        mergedResults.add(object);
                    }
                }
                results = mergedResults;
            }
        }
        return results;
    }
}
