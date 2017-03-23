/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Nov 8, 2013
 */
package com.KyleDing.imcache.cache.search.criteria;

import java.util.List;

import com.KyleDing.imcache.cache.search.index.CacheIndex;

/**
 * The Class BetweenCriteria is used to retrieve items greater than lowerBound
 * and less than upperBound.
 */
public class BetweenCriteria extends ArithmeticCriteria {

    /** The upper bound. */
    private Object upperBound;

    /**
     * Instantiates a new between criteria.
     *
     * @param attributeName the attribute name
     * @param lowerBound the lower bound
     * @param upperBound the upper bound
     */
    public BetweenCriteria(String attributeName, Object lowerBound, Object upperBound) {
        super(attributeName, lowerBound);
        this.upperBound = upperBound;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.criteria.Criteria#meets(com.KyleDing.
     * imcache.cache.search.index.CacheIndex)
     */
    public List<Object> meets(CacheIndex cacheIndex) {
        return cacheIndex.between(value, upperBound);
    }

}
