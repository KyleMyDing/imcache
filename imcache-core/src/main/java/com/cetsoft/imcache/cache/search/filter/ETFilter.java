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
 * The Class ETFilter is used to retrieve items equals to the given value.
 */
public class ETFilter extends ArithmeticFilter {

    /**
     * Instantiates a new eT filter.
     *
     * @param attributeName the attribute name
     * @param expectedValue the expected value
     */
    public ETFilter(String attributeName, Object expectedValue) {
        super(attributeName, expectedValue);
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
        List<Object> result = new ArrayList<Object>(objects.size());
        for (Object object : objects) {
            Comparable objectValue = (Comparable) getAttributeValue(object);
            if (objectValue.compareTo(value) == 0) {
                result.add(object);
            }
        }
        return result;
    }

}
