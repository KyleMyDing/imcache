/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 17, 2015
 */
package com.KyleDing.imcache.redis.client;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MultiRedisClientTest {

    byte[] key = { '0' };
    byte[] value = { '0' };

    @Mock
    Client client;

    MultiRedisClient redisClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        redisClient = spy(new MultiRedisClient("", 0));
        doReturn(client).when(redisClient).getClient();
    }

    @Test(expected = IllegalArgumentException.class)
    public void redisClientThrowsIllegalArgument() {
        new MultiRedisClient("", 1, 0);
    }

    @Test
    public void ping() throws ConnectionException, IOException {
        redisClient.ping();
        verify(client).ping();
    }

    @Test
    public void set() throws ConnectionException, IOException {
        redisClient.set(key, value);
        verify(client).set(key, value);
    }

    @Test
    public void get() throws ConnectionException, IOException {
        doReturn(value).when(client).get(key);
        byte[] actualValue = redisClient.get(key);
        assertEquals(value, actualValue);
    }

    @Test
    public void expire() throws ConnectionException, IOException {
        doReturn(value).when(client).expire(key);
        byte[] actualValue = redisClient.expire(key);
        assertEquals(value, actualValue);
    }

    @Test
    public void flushdb() throws ConnectionException, IOException {
        redisClient.flushdb();
        verify(client).flushdb();
    }

    @Test
    public void dbsize() throws ConnectionException, IOException {
        int size = 5;
        doReturn(size).when(client).dbsize();
        int actualSize = redisClient.dbsize();
        assertEquals(size, actualSize);
    }

    @Test
    public void getClient() throws ConnectionException, IOException {
        MultiRedisClient redisClient = new MultiRedisClient("", 2);
        Client firstClient = redisClient.getClient();
        redisClient.getClient();
        redisClient.getClient();
        assertEquals(firstClient, redisClient.getClient());
    }

}
