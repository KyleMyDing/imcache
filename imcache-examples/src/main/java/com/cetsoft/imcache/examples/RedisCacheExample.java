/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 18, 2015
 */
package com.KyleDing.imcache.examples;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.builder.CacheBuilder;

public class RedisCacheExample {

    static final String HOSTNAME = "localhost";
    static final int PORT = 6379;

    public static void example() {
        //If hostname and port aren't given, default port and
        //hostname will be used.
        Cache<Integer, String> cache = CacheBuilder.redisCache().hostName(HOSTNAME).port(PORT).build();
        cache.put(1, "apple");
        System.out.println(cache.get(1));
    }

    public static void main(String[] args) {
        example();
    }

}
