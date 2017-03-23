/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jan 6, 2014
 */
package com.KyleDing.imcache.examples;

import java.util.ArrayList;
import java.util.List;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.CacheEntry;
import com.KyleDing.imcache.cache.builder.CacheBuilder;
import com.KyleDing.imcache.cache.populator.ConcurrentCachePopulator;
import com.KyleDing.imcache.cache.util.CacheUtils;

/**
 * The Class CachePopulatorExample.
 */
public class CachePopulatorExample extends ConcurrentCachePopulator<String, String> {

    /**
     * Instantiates a new cache populator example.
     *
     * @param cache the cache
     */
    public CachePopulatorExample(Cache<String, String> cache) {
        super(cache);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.cache.CachePopulator#loadEntries()
     */
    public List<CacheEntry<String, String>> loadEntries() {
        final int SIZE = 3;
        List<CacheEntry<String, String>> cacheEntries = new ArrayList<CacheEntry<String, String>>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            cacheEntries.add(CacheUtils.createEntry("" + i, "" + i));
        }
        return cacheEntries;
    }

    public static void example() {
        Cache<String, String> cache = CacheBuilder.concurrentHeapCache().build();
        CachePopulatorExample populatorExample = new CachePopulatorExample(cache);
        populatorExample.pupulate();
        System.out.println(cache.get("0"));
    }

    public static void main(String[] args) {
        example();
    }

}
