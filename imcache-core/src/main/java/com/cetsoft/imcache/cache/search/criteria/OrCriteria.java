/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Nov 8, 2013
 */
package com.KyleDing.imcache.cache.search.criteria;

/**
 * The Class AndCriteria is used to retrieve items union for the criterias.
 */
public class OrCriteria extends LogicalCriteria {

    /** The criterias. */
    private Criteria[] criterias;

    /**
     * Instantiates a new or criteria.
     *
     * @param criterias the criterias
     */
    public OrCriteria(Criteria... criterias) {
        this.criterias = criterias;
    }

    /**
     * Gets the criterias.
     *
     * @return the criterias
     */
    public Criteria[] getCriterias() {
        return criterias;
    }
}
