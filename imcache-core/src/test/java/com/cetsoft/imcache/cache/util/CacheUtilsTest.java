/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 3, 2015
 */
package com.KyleDing.imcache.cache.util;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.KyleDing.imcache.cache.CacheEntry;

public class CacheUtilsTest {

    @Test
    public void createEntry() {
        CacheEntry<Integer, Integer> cacheEntry = CacheUtils.createEntry(1, 2);
        assertEquals(new Integer(1), cacheEntry.getKey());
        assertEquals(new Integer(2), cacheEntry.getValue());
    }
}
