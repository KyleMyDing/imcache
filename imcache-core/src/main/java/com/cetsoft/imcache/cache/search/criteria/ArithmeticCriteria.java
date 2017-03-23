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
 * The Class ArithmeticCriteria is used for arithmetic operations.
 */
public abstract class ArithmeticCriteria extends LogicalCriteria {

    /** The attribute name. */
    private String attributeName;

    /** The expected value. */
    protected Object value;

    /**
     * Instantiates a new equals to criteria.
     *
     * @param attributeName the attribute name
     * @param value the expected value
     */
    public ArithmeticCriteria(String attributeName, Object value) {
        this.attributeName = attributeName;
        this.value = value;
    }

    /**
     * Gets the attribute name.
     *
     * @return the attribute name
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * Meets the given criteria.
     *
     * @param cacheIndex the cache index
     * @return the list
     */
    public abstract List<Object> meets(CacheIndex cacheIndex);

}
