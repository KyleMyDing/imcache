/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 20, 2014
 */
package com.KyleDing.imcache.examples;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.KyleDing.imcache.cache.populator.SimpleCachePopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.CacheEntry;
import com.KyleDing.imcache.cache.util.CacheUtils;

/**
 * The Class SpringConfigurationExample.
 */
@Component
public class SpringConfigurationExample {

    /** The cache. */
    @Autowired
    Cache<String, String> cache;

    /** The cache dao. */
    final CacheDao cacheDao = new CacheDaoImpl();

    public static void example() {
        @SuppressWarnings({ "resource", "unused" })
        ApplicationContext context = new ClassPathXmlApplicationContext("exampleContext.xml");
    }

    /**
     * Inits the cache.
     */
    @PostConstruct
    public void initCache() {
        new SimpleCachePopulator<String, String>(cache) {
            public List<CacheEntry<String, String>> loadEntries() {
                List<CacheEntry<String, String>> cacheEntries = new ArrayList<CacheEntry<String, String>>();
                for (String cacheEntry : cacheDao.getAll()) {
                    cacheEntries.add(CacheUtils.createEntry(cacheEntry, cacheEntry));
                }
                return cacheEntries;
            }
        }.pupulate();
        System.out.println(cache.get("orange"));
    }

    /**
     * The Interface CacheDao.
     */
    protected static interface CacheDao {

        /**
         * Gets the all.
         *
         * @return the all
         */
        List<String> getAll();
    }

    /**
     * The Class CacheDaoImpl.
     */
    protected static class CacheDaoImpl implements CacheDao {

        /*
         * (non-Javadoc)
         *
         * @see
         * com.KyleDing.imcache.examples.SpringConfigurationExample.CacheDao#
         * getAll()
         */
        public List<String> getAll() {
            List<String> fruits = new ArrayList<String>();
            fruits.add("orange");
            fruits.add("apple");
            fruits.add("kiwi");
            return fruits;
        }
    }

    public static void main(String[] args) {
        example();
    }
}
