/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jan 6, 2014
 */
package com.KyleDing.imcache.cache.builder;

/**
 * The Class NecessaryArgumentException.
 */
public class NecessaryArgumentException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7420658148641484150L;

    /**
     * Instantiates a new necessary argument exception.
     *
     * @param exception the exception
     */
    public NecessaryArgumentException(String exception) {
        super(exception);
    }
}
