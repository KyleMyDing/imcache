/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 17, 2015
 */
package com.KyleDing.imcache.cache.builder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.KyleDing.imcache.cache.Cache;
import com.KyleDing.imcache.cache.CacheLoader;
import com.KyleDing.imcache.cache.EvictionListener;
import com.KyleDing.imcache.serialization.Serializer;

public abstract class AbstractCacheBuilder {

    /** The Constant CACHE_LOADER. */
    protected static final CacheLoader<Object, Object> CACHE_LOADER = new CacheLoader<Object, Object>() {
        public Object load(Object key) {
            return null;
        }
    };

    /** The Constant EVICTION_LISTENER. */
    protected static final EvictionListener<Object, Object> EVICTION_LISTENER = new EvictionListener<Object, Object>() {
        public void onEviction(Object key, Object value) {
        }
    };

    /** The Constant SERIALIZER. */
    protected static final Serializer<Object> SERIALIZER = new Serializer<Object>() {
        public byte[] serialize(Object object) {
            byte[] objectBytes = null;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                ObjectOutput out = new ObjectOutputStream(bos);
                out.writeObject(object);
                objectBytes = bos.toByteArray();
                out.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return objectBytes;
        }

        public Object deserialize(byte[] bytes) {
            Object object = null;
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            try {
                ObjectInput in = new ObjectInputStream(bis);
                object = in.readObject();
                bis.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return object;
        }
    };

    /** The cache loader. */
    protected CacheLoader<Object, Object> cacheLoader;

    /** The eviction listener. */
    protected EvictionListener<Object, Object> evictionListener;

    /**
     * Instantiates a new cache builder.
     */
    protected AbstractCacheBuilder() {
        cacheLoader = CACHE_LOADER;
        evictionListener = EVICTION_LISTENER;
    }

    /**
     * Builds the cache.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return the cache
     */
    public abstract <K, V> Cache<K, V> build();

    /**
     * Builds the cache.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param cacheName the cache name
     * @return the searchable cache
     */
    public <K, V> Cache<K, V> build(String cacheName) {
        Cache<K, V> cache = build();
        cache.setName(cacheName);
        return cache;
    }
}
