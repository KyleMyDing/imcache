/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Oct 26, 2013
 */
package com.KyleDing.imcache.cache.search.index;

import java.util.List;

/**
 * The Class CacheIndexBase throws UnsupportedOperationException for all methods
 * to be implemented.
 */
public abstract class CacheIndexBase implements CacheIndex {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.index.CacheIndex#equalsTo(java.lang.
     * Object)
     */
    public List<Object> equalsTo(Object expectedValue) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.index.CacheIndex#lessThan(java.lang.
     * Object)
     */
    public List<Object> lessThan(Object value) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.index.CacheIndex#lessThanOrEqualsTo(
     * java.lang.Object)
     */
    public List<Object> lessThanOrEqualsTo(Object value) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.index.CacheIndex#greaterThan(java.lang
     * .Object)
     */
    public List<Object> greaterThan(Object value) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.index.CacheIndex#greaterThanOrEqualsTo
     * (java.lang.Object)
     */
    public List<Object> greaterThanOrEqualsTo(Object value) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.index.CacheIndex#between(java.lang.Object
     * , java.lang.Object)
     */
    public List<Object> between(Object lowerBound, Object upperBound) {
        throw new UnsupportedOperationException();
    }

}
