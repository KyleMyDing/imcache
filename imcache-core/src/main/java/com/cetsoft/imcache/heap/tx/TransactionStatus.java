/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 20, 2014
 */
package com.KyleDing.imcache.heap.tx;

/**
 * The Enum TransactionStatus.
 */
public enum TransactionStatus {

    /** The active. */
    ACTIVE,

    /** The began. */
    BEGAN,

    /** The beginning. */
    BEGINNING,

    /** The committing. */
    COMMITTING,

    /** The committed. */
    COMMITTED,

    /** The commit failed. */
    COMMIT_FAILED,

    /** The rolling back. */
    ROLLING_BACK,

    /** The rolled back. */
    ROLLED_BACK,

    /** The completed. */
    COMPLETED
}
