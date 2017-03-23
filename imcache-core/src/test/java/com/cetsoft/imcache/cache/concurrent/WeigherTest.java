/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 4, 2015
 */
package com.KyleDing.imcache.cache.concurrent;

import org.junit.Test;

import com.KyleDing.imcache.concurrent.Weighers;

public class WeigherTest {

    @Test
    public void weigherTest() {
        Weighers.byteArray();
        Weighers.collection();
        Weighers.iterable();
        Weighers.iterable();
        Weighers.list();
        Weighers.map();
        Weighers.set();
    }
}
