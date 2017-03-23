/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 4, 2015
 */
package com.KyleDing.imcache.cache.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.junit.Test;
import static org.junit.Assert.*;

import com.KyleDing.imcache.concurrent.ConcurrentLinkedHashMap;
import com.KyleDing.imcache.concurrent.EvictionListener;
import com.KyleDing.imcache.concurrent.Weigher;
import com.KyleDing.imcache.concurrent.Weighers;

public class ConcurrentLinkedHashMapTest {

    @Test
    public void concurrentLinkedHashMap() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        int concurrencyLevel = 2;
        int initialCapacity = 2;
        int capacity = 2;
        long period = 3L;
        EvictionListener<Integer, Integer> evictionListener = new EvictionListener<Integer, Integer>() {
            @Override
            public void onEviction(Integer key, Integer value) {
            }
        };
        Weigher<Integer> weigher = Weighers.singleton();
        ConcurrentLinkedHashMap<Integer, Integer> map = new ConcurrentLinkedHashMap<Integer, Integer>(concurrencyLevel,
                capacity, initialCapacity, weigher, period, service, evictionListener);
        map.put(1, 2);
        map.ascendingKeySet();
        map.ascendingKeySetWithLimit(10);
        map.ascendingMap();
        map.ascendingMapWithLimit(10);
        assertEquals(capacity, map.capacity());
        assertFalse(map.containsKey(3));
        map.descendingKeySet();
        map.descendingKeySetWithLimit(10);
        map.descendingMap();
        map.descendingMapWithLimit(10);
        map.entrySet();
        assertTrue(map.keySet().contains(1));
        map.putAll(map);
        map.putIfAbsent(3, 4);
        map.remove(3);
        map.replace(1, 10);
        map.replace(1, 10, 100);
        map.setCapacity(20);
        map.size();
        map.isEmpty();
        map.weightedSize();
    }
}
