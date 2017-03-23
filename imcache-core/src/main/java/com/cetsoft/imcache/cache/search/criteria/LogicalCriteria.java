/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Nov 8, 2013
 */
package com.KyleDing.imcache.cache.search.criteria;

/**
 * The Class LogicalCriteria is used for logical operations.
 */
public abstract class LogicalCriteria implements Criteria {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.criteria.Criteria#and(com.KyleDing.imcache
     * .cache.search.criteria.Criteria)
     */
    public Criteria and(Criteria criteria) {
        return new AndCriteria(this, criteria);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.criteria.Criteria#or(com.KyleDing.imcache
     * .cache.search.criteria.Criteria)
     */
    public Criteria or(Criteria criteria) {
        return new OrCriteria(this, criteria);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.search.criteria.Criteria#diff(com.KyleDing.imcache
     * .cache.search.criteria.Criteria)
     */
    public Criteria diff(Criteria criteria) {
        return new DiffCriteria(this, criteria);
    }

}
