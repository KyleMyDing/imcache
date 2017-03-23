/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 20, 2014
 */
package com.KyleDing.imcache.offheap;

/**
 * The Class StaleItemException is thrown where local cache item does not have
 * the latest version of the cache item.
 */
public class StaleItemException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5688389152095338751L;

    /**
     * Instantiates a new stale item exception.
     *
     * @param expectedVersion the expected version
     * @param actualVersion the actual version
     */
    public StaleItemException(int expectedVersion, int actualVersion) {
        super("Expected version for the item is " + expectedVersion + " but the actual value was " + actualVersion);
    }

}
