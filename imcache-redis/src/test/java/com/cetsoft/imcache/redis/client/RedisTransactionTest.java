/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 17, 2015
 */
package com.KyleDing.imcache.redis.client;

import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RedisTransactionTest {

    final RedisTransaction transaction = spy(new RedisTransaction());

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void openClose() {
        Thread thread = Thread.currentThread();
        transaction.open();
        assertEquals(transaction.threadInTransaction.get(), thread);
        transaction.close();
        assertEquals(transaction.threadInTransaction.get(), null);
    }

    @Test
    public void openCloseSameThread() {
        Thread thread = Thread.currentThread();
        transaction.open();
        transaction.open();
        assertEquals(transaction.threadInTransaction.get(), thread);
        transaction.close();
        assertEquals(transaction.threadInTransaction.get(), null);
    }

    @Test
    public void openCloseAnotherThreadInTransaction() {
        int noOfThreads = 5;
        final CountDownLatch countDownLatch = new CountDownLatch(noOfThreads);
        for (int i = 0; i < noOfThreads; i++) {
            final int multiplier = i * 10;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    transaction.open();
                    try {
                        Thread.sleep(multiplier);
                    } catch (InterruptedException e) {
                    }
                    transaction.close();
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
