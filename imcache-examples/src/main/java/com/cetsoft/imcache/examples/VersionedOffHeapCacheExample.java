/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 20, 2014
 */
package com.KyleDing.imcache.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.SimpleItem;
import com.KyleDing.imcache.cache.VersionedItem;
import com.KyleDing.imcache.cache.builder.CacheBuilder;
import com.KyleDing.imcache.offheap.StaleItemException;
import com.KyleDing.imcache.offheap.bytebuffer.OffHeapByteBufferStore;

/**
 * The Class VersionedOffHeapCacheExample.
 */
public class VersionedOffHeapCacheExample {

    public static void example() {
        OffHeapByteBufferStore bufferStore = new OffHeapByteBufferStore(10000, 10);
        final Cache<String, VersionedItem<String>> cache = CacheBuilder.versionedOffHeapCache().storage(bufferStore)
                .build();
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100000; i++) {
            service.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (true) {
                        try {
                            VersionedItem<String> value = cache.get("apple");
                            String newValue = getRandomString();
                            if (value == null) {
                                cache.put("apple", new SimpleItem<String>(newValue));
                            } else {
                                value.update(newValue);
                                cache.put("apple", value);
                            }
                            System.out.println(cache.get("apple").getValue());
                            break;
                        } catch (StaleItemException ex) {
                            ex.printStackTrace();
                        }
                    }
                    VersionedItem<String> item = cache.get("apple");
                    System.out.println(item.getVersion());
                }
            });
        }
    }

    /**
     * Gets the random string.
     *
     * @return the random string
     */
    private static String getRandomString() {
        char[] chars = new char[5];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (Math.random() * 25 + 65);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        example();
    }
}
