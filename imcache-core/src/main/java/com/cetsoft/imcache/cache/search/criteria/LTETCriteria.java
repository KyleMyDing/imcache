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
 * The Class LTETCriteria is used to retrieve items less than or equals to the
 * given value.
 */
public class LTETCriteria extends ArithmeticCriteria {

    /**
     * Instantiates a new lTET criteria.
     *
     * @param attributeName the attribute name
     * @param value the value
     */
    public LTETCriteria(String attributeName, Object value) {
        super(attributeName, value);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.criteria.Criteria#meets(com.KyleDing.
     * imcache.cache.search.index.CacheIndex)
     */
    public List<Object> meets(CacheIndex cacheIndex) {
        return cacheIndex.lessThanOrEqualsTo(value);
    }

}
