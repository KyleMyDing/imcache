/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 23, 2013
 */
package com.KyleDing.imcache.heap.tx;

/**
 * The Class TransactionException.
 */
public class TransactionException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1513928931641021361L;

    /**
     * Instantiates a new transaction exception.
     *
     * @param exception the exception
     */
    public TransactionException(String exception) {
        super(exception);
    }

    /**
     * Instantiates a new transaction exception.
     *
     * @param exception the exception
     */
    public TransactionException(Exception exception) {
        super(exception);
    }
}
