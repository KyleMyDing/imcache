/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 18, 2015
 */
package com.KyleDing.imcache.cache.builder;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.CacheLoader;
import com.KyleDing.imcache.cache.EvictionListener;
import com.KyleDing.imcache.redis.RedisCache;
import com.KyleDing.imcache.redis.client.Client;
import com.KyleDing.imcache.redis.client.Connection;
import com.KyleDing.imcache.redis.client.MultiRedisClient;
import com.KyleDing.imcache.serialization.Serializer;

/**
 * The Class RedisCacheBuilder.
 */
public class RedisCacheBuilder extends AbstractCacheBuilder {

    /** The port. */
    private int port = Connection.DEFAULT_PORT;

    /** The host name. */
    private String hostName = Connection.DEFAULT_HOST;

    /** The concurrency level. */
    private int concurrencyLevel = MultiRedisClient.CONCURRENCY_LEVEL;

    /** The serializer. */
    private Serializer<Object> serializer = AbstractCacheBuilder.SERIALIZER;

    /**
     * Cache loader.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param cacheLoader the cache loader
     * @return the heap cache builder
     */
    @SuppressWarnings("unchecked")
    public <K, V> RedisCacheBuilder cacheLoader(CacheLoader<K, V> cacheLoader) {
        this.cacheLoader = (CacheLoader<Object, Object>) cacheLoader;
        return this;
    }

    /**
     * Eviction listener.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param evictionListener the eviction listener
     * @return the heap cache builder
     */
    @SuppressWarnings("unchecked")
    public <K, V> RedisCacheBuilder evictionListener(EvictionListener<K, V> evictionListener) {
        this.evictionListener = (EvictionListener<Object, Object>) evictionListener;
        return this;
    }

    /**
     * Serializer.
     *
     * @param <V> the value type
     * @param serializer the serializer
     * @return the off heap cache builder
     */
    public <V> RedisCacheBuilder serializer(Serializer<Object> serializer) {
        this.serializer = (Serializer<Object>) serializer;
        return this;
    }

    /**
     * Host name.
     *
     * @param hostName the host name
     * @return the redis cache builder
     */
    public RedisCacheBuilder hostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    /**
     * Port.
     *
     * @param port the port
     * @return the redis cache builder
     */
    public RedisCacheBuilder port(int port) {
        this.port = port;
        return this;
    }

    /**
     * Concurrency level.
     *
     * @param concurrencyLevel the concurrency level
     * @return the redis cache builder
     */
    public RedisCacheBuilder concurrencyLevel(int concurrencyLevel) {
        this.concurrencyLevel = concurrencyLevel;
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.cache.builder.AbstractCacheBuilder#build()
     */
    @Override
    @SuppressWarnings("unchecked")
    public <K, V> Cache<K, V> build() {
        Client client = new MultiRedisClient(hostName, port, concurrencyLevel);
        return new RedisCache<K, V>((CacheLoader<K, V>) cacheLoader, (EvictionListener<K, V>) evictionListener,
                serializer, client);
    }

}
