/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 12, 2015
 */
package com.KyleDing.imcache.redis.client;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RedisClientTest {

    @Mock
    Connection connection;

    @Mock
    RedisCommandExecutor commandExecutor;

    @Mock
    RedisCommandResult commandResult;

    RedisClient redisClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        redisClient = spy(new RedisClient(connection));
        redisClient.commandExecutor = commandExecutor;
        redisClient.commandResult = commandResult;
    }

    @Test
    public void constructors() {
        new RedisClient();
        new RedisClient("hostname");
        new RedisClient("hostname", 1212);
    }

    @Test
    public void ping() throws ConnectionException, IOException {
        doReturn("OK").when(commandResult).getStatus();
        redisClient.ping();
        verify(redisClient).runVoidCommand(RedisCommands.PING);
        verify(commandExecutor).execute(RedisCommands.PING);
    }

    @Test(expected = ConnectionException.class)
    public void pingThrowsConnectionException() throws ConnectionException, IOException {
        doReturn("ADA").when(commandResult).getStatus();
        redisClient.ping();
    }

    @Test
    public void flushdb() throws ConnectionException, IOException {
        doReturn("OK").when(commandResult).getStatus();
        redisClient.flushdb();
        verify(redisClient).runVoidCommand(RedisCommands.FLUSHDB);
        verify(commandExecutor).execute(RedisCommands.FLUSHDB);
    }

    @Test
    public void set() throws ConnectionException, IOException {
        byte[] key = { '1' };
        byte[] value = { '1' };
        doReturn("OK").when(commandResult).getStatus();
        redisClient.set(key, value);
        verify(redisClient).runVoidCommand(RedisCommands.SET, key, value);
        verify(commandExecutor).execute(RedisCommands.SET, key, value);
    }

    @Test
    public void get() throws ConnectionException, IOException {
        byte[] key = { '1' };
        byte[] value = { '1' };
        doReturn(value).when(commandResult).getBytes();
        byte[] actualValue = redisClient.get(key);
        verify(commandExecutor).execute(RedisCommands.GET, key);
        assertEquals(value, actualValue);
    }

    @Test
    public void dbsize() throws ConnectionException, IOException {
        int size = 3;
        doReturn(size).when(commandResult).getInt();
        int actualSize = redisClient.dbsize();
        verify(commandExecutor).execute(RedisCommands.DBSIZE);
        assertEquals(size, actualSize);
    }

    @Test
    public void expire() throws ConnectionException, IOException {
        byte[] key = { '1' };
        byte[] value = { '1' };
        doReturn(value).when(commandResult).getBytes();
        doReturn(1).when(commandResult).getInt();
        byte[] actualValue = redisClient.expire(key);
        verify(commandExecutor).execute(RedisCommands.GET, key);
        verify(commandExecutor).execute(RedisCommands.EXPIRE, key, new byte[] { '0' });
        assertEquals(value, actualValue);
    }
}
