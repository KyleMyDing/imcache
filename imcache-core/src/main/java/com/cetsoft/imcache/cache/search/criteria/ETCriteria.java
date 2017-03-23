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
 * The Class ETCriteria is used to retrieve items equals to the given value.
 */
public class ETCriteria extends ArithmeticCriteria {

    /**
     * Instantiates a new eT criteria.
     *
     * @param attributeName the attribute name
     * @param expectedValue the expected value
     */
    public ETCriteria(String attributeName, Object expectedValue) {
        super(attributeName, expectedValue);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.criteria.Criteria#meets(com.KyleDing.
     * imcache.cache.search.index.CacheIndex)
     */
    public List<Object> meets(CacheIndex cacheIndex) {
        return cacheIndex.equalsTo(value);
    }

}
