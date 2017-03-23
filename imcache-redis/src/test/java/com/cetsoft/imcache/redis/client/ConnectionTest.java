/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 5, 2015
 */
package com.KyleDing.imcache.redis.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.KyleDing.imcache.redis.client.Connection;
import com.KyleDing.imcache.redis.client.ConnectionException;
import com.KyleDing.imcache.redis.client.RedisStreamWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConnectionTest {

    Connection connection;

    @Mock
    Socket socket;

    @Mock
    InetSocketAddress inetSocketAddress;

    @Mock
    RedisStreamWriter redisOutputStream;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        connection = spy(new Connection());
        connection.socket = socket;
        connection.streamWriter = redisOutputStream;
    }

    @Test
    public void open() throws ConnectionException, IOException {
        doReturn(false).when(connection).isConnected();
        doReturn(socket).when(connection).createSocket();
        connection.open();
        assertNotNull(connection.getStreamReader());
        assertNotNull(connection.getStreamWriter());
        verify(socket).setSoTimeout(Connection.DEFAULT_SOCKET_TIMEOUT);
    }

    @Test(expected = ConnectionException.class)
    public void openThrowsConnectionException() throws ConnectionException, IOException {
        doReturn(false).when(connection).isConnected();
        doReturn(socket).when(connection).createSocket();
        doThrow(new SocketException()).when(socket).setSoTimeout(Connection.DEFAULT_SOCKET_TIMEOUT);
        connection.open();
    }

    @Test(expected = ConnectionException.class)
    public void closeThrowsConnectionException() throws ConnectionException, IOException {
        doReturn(true).when(connection).isConnected();
        doThrow(new IOException()).when(socket).close();
        doNothing().when(redisOutputStream).flush();
        connection.close();
        verify(socket, times(2)).close();
    }

    @Test
    public void isConnectedReturnsFalse() throws ConnectionException, IOException {
        doReturn(false).when(socket).isBound();
        assertFalse(connection.isConnected());
    }

    @Test
    public void isConnectedReturnsTrue() throws ConnectionException, IOException {
        doReturn(false).when(socket).isBound();
        doReturn(false).when(socket).isClosed();
        doReturn(true).when(socket).isConnected();
        doReturn(false).when(socket).isInputShutdown();
        doReturn(false).when(socket).isOutputShutdown();
        assertFalse(connection.isConnected());
    }
}
