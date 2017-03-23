/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 29, 2013
 */
package com.KyleDing.imcache.cache.search.index;

import java.util.List;

/**
 * The Interface CacheIndex is a data structure that improves the speed of data
 * retrieval operations on a cache at the cost of additional writes and the use
 * of more storage space to maintain the extra copy of data. Indexes are used to
 * quickly locate data without having to search every object in a cache.
 */
public interface CacheIndex {

    /**
     * Puts index on indexedKey.
     *
     * @param indexedKey the indexed key
     * @param key the key
     */
    void put(Object indexedKey, Object key);

    /**
     * Removes the index from indexedKey.
     *
     * @param indexedKey the indexed key
     * @param key the key
     */
    void remove(Object indexedKey, Object key);

    /**
     * Equals to.
     *
     * @param expectedValue the expected value
     * @return the list
     */
    List<Object> equalsTo(Object expectedValue);

    /**
     * Less than.
     *
     * @param value the value
     * @return the list
     */
    List<Object> lessThan(Object value);

    /**
     * Less than or equals to.
     *
     * @param value the value
     * @return the list
     */
    List<Object> lessThanOrEqualsTo(Object value);

    /**
     * Greater than.
     *
     * @param value the value
     * @return the list
     */
    List<Object> greaterThan(Object value);

    /**
     * Greater than or equals to.
     *
     * @param value the value
     * @return the list
     */
    List<Object> greaterThanOrEqualsTo(Object value);

    /**
     * Between.
     *
     * @param lowerBound the lower bound
     * @param upperBound the upper bound
     * @return the list
     */
    List<Object> between(Object lowerBound, Object upperBound);

}
