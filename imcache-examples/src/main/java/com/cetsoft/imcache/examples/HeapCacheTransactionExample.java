/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 23, 2013
 */
package com.KyleDing.imcache.examples;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.builder.CacheBuilder;
import com.KyleDing.imcache.heap.tx.CacheTransaction;
import com.KyleDing.imcache.heap.tx.Transaction;
import com.KyleDing.imcache.heap.tx.TransactionCommitter;
import com.KyleDing.imcache.heap.tx.TransactionException;

/**
 * The Class HeapCacheTransactionTest.
 */
public class HeapCacheTransactionExample {

    public static void example() {
        Cache<Integer, Integer> cache = CacheBuilder.transactionalHeapCache()
                .transactionCommitter(new TransactionCommitter<Integer, Integer>() {
                    int counter = 0;

                    public void doPut(Integer key, Integer value) {
                        if (counter < 3) {
                            System.out.println("key[" + key + "]," + "value[" + value + "]");
                            counter++;
                        } else {
                            throw new RuntimeException();
                        }
                    }
                }).build();
        Transaction transaction1 = CacheTransaction.get();
        transaction1.begin();
        try {
            cache.put(3, 5);
            cache.put(10, 14);
            transaction1.commit();
        } catch (TransactionException exception) {
            transaction1.rollback();
        } finally {
            transaction1.close();
        }
        System.out.println("Value for the key 3 is " + cache.get(3));
        System.out.println("Value for the key 10 is " + cache.get(10));
        Transaction transaction2 = CacheTransaction.get();
        transaction2.begin();
        try {
            cache.put(1, 10);
            cache.put(10, 13);
            transaction2.commit();
        } catch (TransactionException exception) {
            transaction2.rollback();
        } finally {
            transaction2.close();
        }
        System.out.println("Value for the key 1 is " + cache.get(1));
        System.out.println("Value for the key 10 is " + cache.get(10));
    }

    public static void main(String[] args) {
        example();
    }
}
