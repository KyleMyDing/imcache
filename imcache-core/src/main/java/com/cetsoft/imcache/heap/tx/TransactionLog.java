/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 23, 2013
 */
package com.KyleDing.imcache.heap.tx;

/**
 * The Interface TransactionLog provides logging operations during a
 * transaction.
 */
public interface TransactionLog {

    /**
     * Applies the log.
     */
    void apply();

    /**
     * Rollbacks the log.
     */
    void rollback();
}
