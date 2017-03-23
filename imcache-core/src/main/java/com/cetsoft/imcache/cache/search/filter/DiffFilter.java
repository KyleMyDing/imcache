/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Nov 8, 2013
 */
package com.KyleDing.imcache.cache.search.filter;

import java.util.List;

/**
 * The Class DiffFilter.
 */
public class DiffFilter extends LogicalFilter {

    /** The right filter. */
    private Filter leftFilter, rightFilter;

    /**
     * Instantiates a new diff filter.
     *
     * @param leftFilter the left filter
     * @param rightFilter the right filter
     */
    public DiffFilter(Filter leftFilter, Filter rightFilter) {
        this.leftFilter = leftFilter;
        this.rightFilter = rightFilter;
    }

    /**
     * Gets the left filter.
     *
     * @return the left filter
     */
    public Filter getLeftFilter() {
        return leftFilter;
    }

    /**
     * Gets the right filter.
     *
     * @return the right filter
     */
    public Filter getRightFilter() {
        return rightFilter;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.filter.Filter#filter(java.util.List)
     */
    public List<Object> filter(List<Object> objects) {
        List<Object> leftResult = leftFilter.filter(objects);
        List<Object> rightResult = rightFilter.filter(objects);
        for (Object object : rightResult) {
            leftResult.remove(object);
        }
        return leftResult;
    }

}
