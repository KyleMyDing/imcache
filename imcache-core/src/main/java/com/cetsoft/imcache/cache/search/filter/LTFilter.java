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
 * The Class LEFilter is used to retrieve items less than given value.
 */
public class LTFilter extends ArithmeticFilter {

    /**
     * Instantiates a new lE filter.
     *
     * @param attributeName the attribute name
     * @param value the value
     */
    public LTFilter(String attributeName, Object value) {
        super(attributeName, value);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.filter.Filter#filter(java.util.List)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Object> filter(List<Object> objects) {
        List<Object> result = new ArrayList<Object>(objects.size());
        for (Object object : objects) {
            Comparable objectValue = (Comparable) getAttributeValue(object);
            if (objectValue.compareTo(value) < 0) {
                result.add(object);
            }
        }
        return result;
    }

}
