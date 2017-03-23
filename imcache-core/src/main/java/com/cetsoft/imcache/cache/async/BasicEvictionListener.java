/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jun 5, 2014
 */
package com.KyleDing.imcache.cache.async;

import java.util.concurrent.atomic.AtomicInteger;

import com.KyleDing.imcache.cache.util.ThreadUtils;

/**
 * The basic eviction listener interface for receiving eviction events. When
 * eviction occurs, this class creates a thread to save the data.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public abstract class BasicEvictionListener<K, V> implements AsyncEvictionListener<K, V> {

    /** The Constant NO_OF_EVICTION_LISTENERS. */
    private static final AtomicInteger NO_OF_EVICTION_LISTENERS = new AtomicInteger();

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.cache.EvictionListener#onEviction(java.lang.Object,
     * java.lang.Object)
     */
    public void onEviction(final K key, final V value) {
        ThreadUtils.createDaemonThread(new Runnable() {
            public void run() {
                save(key, value);
            }
        }, "imcache:basicAsyncEvictionListener(thread=" + NO_OF_EVICTION_LISTENERS.incrementAndGet() + ")").start();
    }

    /**
     * Saves the key value pair.
     *
     * @param key the key
     * @param value the value
     */
    public abstract void save(K key, V value);

}
