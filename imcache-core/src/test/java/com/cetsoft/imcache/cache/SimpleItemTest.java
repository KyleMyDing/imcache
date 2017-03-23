/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 4, 2015
 */
package com.KyleDing.imcache.cache;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleItemTest {

    @Test
    public void simpleItem() {
        SimpleItem<Integer> item = new SimpleItem<Integer>(10);
        assertTrue(10 == item.getValue());
        item.setVersion(2);
        assertTrue(2 == item.getVersion());
        item.update(20);
        assertTrue(20 == item.getValue());
    }
}
