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
 * The Class BetweenFilter is used to retrieve items greater than lowerBound and
 * less than upperBound.
 */
public class BetweenFilter extends ArithmeticFilter {

    /** The upper bound. */
    private Object upperBound;

    /**
     * Instantiates a new between filter.
     *
     * @param attributeName the attribute name
     * @param lowerBound the lower bound
     * @param upperBound the upper bound
     */
    public BetweenFilter(String attributeName, Object lowerBound, Object upperBound) {
        super(attributeName, lowerBound);
        this.upperBound = upperBound;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.filter.Filter#filter(com.KyleDing.imcache
     * .cache.search.index.CacheIndex)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Object> filter(List<Object> objects) {
        List<Object> result = new ArrayList<Object>();
        for (Object object : objects) {
            Comparable objectValue = (Comparable) getAttributeValue(object);
            if (objectValue.compareTo(value) < 0 && objectValue.compareTo(upperBound) > 0) {
                result.add(object);
            }
        }
        return result;
    }

}
