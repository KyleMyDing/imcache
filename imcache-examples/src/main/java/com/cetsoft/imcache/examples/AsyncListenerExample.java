/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jun 7, 2014
 */
package com.KyleDing.imcache.examples;

import java.util.List;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.async.CacheTask;
import com.KyleDing.imcache.cache.async.ScheduledEvictionListener;
import com.KyleDing.imcache.cache.builder.CacheBuilder;

/**
 * The Class AsyncListenerExample.
 */
public class AsyncListenerExample {

    public static void example() {
        final StringDAO stringDAO = new StringDAO();
        Cache<String, String> cache = CacheBuilder.concurrentHeapCache()
                .evictionListener(new ScheduledEvictionListener<String, String>() {
                    @Override
                    public void save(List<CacheTask<String, String>> cacheTasks) {
                        for (CacheTask<String, String> task : cacheTasks) {
                            stringDAO.update(task.getKey(), task.getValue());
                        }
                    }
                }).build();
        cache.put("key", "value");
    }

    /**
     * The Class StringDAO.
     */
    private static class StringDAO {

        public void update(String key, String value) {
        }
    }

    public static void main(String[] args) {
        example();
    }

}
