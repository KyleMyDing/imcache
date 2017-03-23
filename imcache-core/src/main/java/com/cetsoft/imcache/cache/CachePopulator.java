/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jan 4, 2014
 */
package com.KyleDing.imcache.cache;

import java.util.List;

/**
 * The Interface CachePopulator.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface CachePopulator<K, V> {

    /**
     * Populates the given cache with the cache items.
     */
    void pupulate();

    /**
     * Loads entries for the cahce.
     *
     * @return the list
     */
    List<CacheEntry<K, V>> loadEntries();
}
