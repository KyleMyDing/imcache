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
 * The Class LTETFilter is used to retrieve items less than or equals to the
 * given value.
 */
public class LTETFilter extends ArithmeticFilter {

    /**
     * Instantiates a new lTET filter.
     *
     * @param attributeName the attribute name
     * @param value the value
     */
    public LTETFilter(String attributeName, Object value) {
        super(attributeName, value);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.filter.Filter#filter(com.KyleDing.imcache
     * .cache.search.index.CacheIndex)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> filter(List<Object> objects) {
        List<Object> result = new ArrayList<Object>();
        for (Object object : objects) {
            Comparable objectValue = (Comparable) getAttributeValue(object);
            if (objectValue.compareTo(value) <= 0) {
                result.add(object);
            }
        }
        return result;
    }

}
