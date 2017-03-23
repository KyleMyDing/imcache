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
 * The Class GTETFilter is used to retrieve items greater than equals to the
 * given value.
 */
public class GTETFilter extends ArithmeticFilter {

    /**
     * Instantiates a new gTET filter.
     *
     * @param attributeName the attribute name
     * @param value the value
     */
    public GTETFilter(String attributeName, Object value) {
        super(attributeName, value);
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
            if (objectValue.compareTo(value) >= 0) {
                result.add(object);
            }
        }
        return result;
    }

}
