/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 17, 2015
 */
package com.KyleDing.imcache.redis.client;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class RedisTransaction.
 */
public class RedisTransaction implements Transaction {

    /** The transaction lock. */
    Lock transactionLock = new ReentrantLock();

    /** The transaction condition. */
    Condition transactionCondition = transactionLock.newCondition();

    /** The thread in transaction. */
    AtomicReference<Thread> threadInTransaction = new AtomicReference<Thread>();

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.redis.client.Transaction#open()
     */
    @Override
    public void open() {
        Thread currentThread = Thread.currentThread();
        try {
            if (threadInTransaction.get() == null) {
                transactionLock.lock();
                try {
                    if (threadInTransaction.get() == null) {
                        threadInTransaction.set(currentThread);
                        return;
                    }
                } finally {
                    transactionLock.unlock();
                }
                open();
            } else if (currentThread.equals(threadInTransaction.get())) {
                return;
            } else {
                transactionLock.lock();
                try {
                    while (threadInTransaction.get() != null) {
                        transactionCondition.await();
                    }
                } finally {
                    transactionLock.unlock();
                }
                open();
            }
        } catch (InterruptedException e) {
            //ignore interruption.
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.redis.client.Transaction#close()
     */
    @Override
    public void close() {
        transactionLock.lock();
        try {
            threadInTransaction.set(null);
            transactionCondition.signal();
        } finally {
            transactionLock.unlock();
        }
    }

}
