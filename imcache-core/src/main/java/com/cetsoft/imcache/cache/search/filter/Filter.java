/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Nov 8, 2013
 */
package com.KyleDing.imcache.cache.search.filter;

import java.util.List;

/**
 * The Interface Filter for meeting certain condition.
 */
public interface Filter {

    /**
     * And.
     *
     * @param filter the filter
     * @return the filter
     */
    Filter and(Filter filter);

    /**
     * Or.
     *
     * @param filter the filter
     * @return the filter
     */
    Filter or(Filter filter);

    /**
     * Diff.
     *
     * @param filter the filter
     * @return the filter
     */
    Filter diff(Filter filter);

    /**
     * Filter.
     *
     * @param objects the objects
     * @return the list
     */
    List<Object> filter(List<Object> objects);
}
