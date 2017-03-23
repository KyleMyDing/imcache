/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 23, 2013
 */
package com.KyleDing.imcache.heap.tx;

/**
 * The Interface Transaction allows operations to be performed transactionally
 * for the target Cache.
 */
public interface Transaction {

    /**
     * Begin the transaction.
     */
    void begin();

    /**
     * Commit the transaction.
     */
    void commit();

    /**
     * Rollback the transaction.
     */
    void rollback();

    /**
     * Close the transaction.
     */
    void close();

    /**
     * Gets the status.
     *
     * @return the status
     */
    TransactionStatus getStatus();
}
