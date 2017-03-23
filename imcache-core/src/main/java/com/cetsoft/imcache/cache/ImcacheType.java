/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 20, 2014
 */
package com.KyleDing.imcache.cache;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Class ImcacheType is a class to specify cache types.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class ImcacheType<K, V> implements CacheType<K, V> {

    /** The ordinal counter. */
    private static final AtomicInteger ordinalCounter = new AtomicInteger();

    /** The ordinal. */
    private final int ordinal;

    /**
     * Instantiates a new imcache type.
     */
    public ImcacheType() {
        ordinal = ordinalCounter.getAndIncrement();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.cache.CacheType#getType()
     */
    @Override
    public int getType() {
        return ordinal;
    }

}
