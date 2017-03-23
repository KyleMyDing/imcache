/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jun 5, 2014
 */
package com.KyleDing.imcache.cache.async;

import com.KyleDing.imcache.cache.EvictionListener;

/**
 * The listener interface for receiving eviction events. The class that is
 * interested in processing a eviction event implements this interface. When
 * eviction event occurs, that object's onEviction method is invoked.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface AsyncEvictionListener<K, V> extends EvictionListener<K, V> {
}
