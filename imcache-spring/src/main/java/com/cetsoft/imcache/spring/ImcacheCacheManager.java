/*
 * Copyright (C) 2014 Yusuf Aytas, http://www.yusufaytas.com
 *
 *
 * Author : Kyle Ding
 * Date   : Dec 29, 2013
 */
package com.KyleDing.imcache.spring;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.KyleDing.imcache.cache.builder.AbstractCacheBuilder;
import com.KyleDing.imcache.cache.builder.CacheBuilder;

/**
 * The Class ImcacheCacheManager.
 */
public class ImcacheCacheManager implements CacheManager, InitializingBean {

    /** The cache builder. */
    private AbstractCacheBuilder cacheBuilder;

    /** The caches. */
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    /**
     * Instantiates a new imcache cache manager.
     */
    public ImcacheCacheManager() {
        this(CacheBuilder.concurrentHeapCache());
    }

    /**
     * Instantiates a new imcache cache manager.
     *
     * @param cacheBuilder the cache builder
     */
    public ImcacheCacheManager(AbstractCacheBuilder cacheBuilder) {
        this.cacheBuilder = cacheBuilder;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.cache.CacheManager#getCache(java.lang.String)
     */
    public Cache getCache(String name) {
        Cache cache = caches.get(name);
        if (cache == null) {
            ImcacheCache newCache = new ImcacheCache(cacheBuilder.build(name));
            final Cache exCache = caches.putIfAbsent(name, newCache);
            if (exCache != null) {
                cache = exCache;
            } else {
                cache = newCache;
            }
        }
        return cache;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.cache.CacheManager#getCacheNames()
     */
    public Collection<String> getCacheNames() {
        return Collections.unmodifiableCollection(caches.keySet());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {

    }

    /**
     * Gets the cache builder.
     *
     * @return the cache builder
     */
    public AbstractCacheBuilder getCacheBuilder() {
        return cacheBuilder;
    }

    /**
     * Sets the cache builder.
     *
     * @param cacheBuilder the new cache builder
     */
    public void setCacheBuilder(AbstractCacheBuilder cacheBuilder) {
        this.cacheBuilder = cacheBuilder;
    }

}
