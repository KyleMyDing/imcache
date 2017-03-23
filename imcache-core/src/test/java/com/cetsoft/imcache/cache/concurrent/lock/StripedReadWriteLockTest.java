/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 4, 2015
 */
package com.KyleDing.imcache.cache.concurrent.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import com.KyleDing.imcache.concurrent.lock.StripedReadWriteLock;

public class StripedReadWriteLockTest {

    AtomicInteger id = new AtomicInteger();
    static final int STOP = 2;
    StripedReadWriteLock lock = new StripedReadWriteLock();

    @Test
    public void readLockTest() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                assertTrue(doubleReadLock(2));
                latch.countDown();
            }
        }).start();
        assertTrue(doubleReadLock(1));
        latch.await();
    }

    public boolean doubleReadLock(int lockId) {
        lock.readLock(lockId);
        try {
            id.set(lockId);
            while (lockId != STOP && lockId == id.get())
                ;
            return true;
        } finally {
            lock.readUnlock(lockId);
        }
    }

    @Test
    public void readWriteTest() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                assertTrue(doubleWriteLock(2));
                latch.countDown();
            }
        }).start();
        assertTrue(doubleWriteLock(1));
        latch.await();
    }

    public boolean doubleWriteLock(int lockId) {
        lock.writeLock(lockId);
        try {
            id.set(lockId);
            while (lockId != STOP && lockId == id.get())
                ;
            return true;
        } finally {
            lock.writeUnlock(lockId);
        }
    }
}
