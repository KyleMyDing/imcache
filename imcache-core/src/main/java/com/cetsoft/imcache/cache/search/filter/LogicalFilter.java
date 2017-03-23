/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Nov 8, 2013
 */
package com.KyleDing.imcache.cache.search.filter;

/**
 * The Class LogicalFilter.
 */
public abstract class LogicalFilter implements Filter {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.filter.Filter#and(com.KyleDing.imcache
     * .cache.search.filter.Filter)
     */
    public Filter and(Filter filter) {
        return new AndFilter(this, filter);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.filter.Filter#or(com.KyleDing.imcache
     * .cache.search.filter.Filter)
     */
    public Filter or(Filter filter) {
        return new OrFilter(this, filter);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.filter.Filter#diff(com.KyleDing.imcache
     * .cache.search.filter.Filter)
     */
    public Filter diff(Filter filter) {
        return new DiffFilter(this, filter);
    }

}
