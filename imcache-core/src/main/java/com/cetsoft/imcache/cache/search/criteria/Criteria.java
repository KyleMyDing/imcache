/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Nov 8, 2013
 */
package com.KyleDing.imcache.cache.search.criteria;

/**
 * The Interface Criteria for meeting certain condition.
 */
public interface Criteria {

    /**
     * And.
     *
     * @param criteria the criteria
     * @return the criteria
     */
    Criteria and(Criteria criteria);

    /**
     * Or.
     *
     * @param criteria the criteria
     * @return the criteria
     */
    Criteria or(Criteria criteria);

    /**
     * Diff.
     *
     * @param criteria the criteria
     * @return the criteria
     */
    Criteria diff(Criteria criteria);
}
