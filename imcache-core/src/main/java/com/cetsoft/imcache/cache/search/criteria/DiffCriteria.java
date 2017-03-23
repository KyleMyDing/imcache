/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Nov 8, 2013
 */
package com.KyleDing.imcache.cache.search.criteria;

/**
 * The Class DiffCriteria is used to retrieve items difference for the
 * leftCriteria to rightCriteria.
 */
public class DiffCriteria extends LogicalCriteria {

    /** The right criteria. */
    private Criteria leftCriteria, rightCriteria;

    /**
     * Instantiates a new diff criteria.
     *
     * @param leftCriteria the left criteria
     * @param rightCriteria the right criteria
     */
    public DiffCriteria(Criteria leftCriteria, Criteria rightCriteria) {
        this.leftCriteria = leftCriteria;
        this.rightCriteria = rightCriteria;
    }

    /**
     * Gets the left criteria.
     *
     * @return the left criteria
     */
    public Criteria getLeftCriteria() {
        return leftCriteria;
    }

    /**
     * Gets the right criteria.
     *
     * @return the right criteria
     */
    public Criteria getRightCriteria() {
        return rightCriteria;
    }

}
