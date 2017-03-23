/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 14, 2015
 */
package com.KyleDing.imcache.redis.client;

import org.junit.Test;

import static org.junit.Assert.*;

public class RedisCommandsTest {

    @Test
    public void getBytes() {
        byte[] expectedGetBytes = new byte[] { 71, 69, 84 };
        byte[] expectedDBSizeBytes = new byte[] { 68, 66, 83, 73, 90, 69 };
        assertArrayEquals(expectedGetBytes, RedisCommands.GET.getBytes());
        assertArrayEquals(expectedDBSizeBytes, RedisCommands.DBSIZE.getBytes());
    }
}
