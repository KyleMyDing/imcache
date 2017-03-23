/*
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 12, 2015
 */
package com.KyleDing.imcache.redis.client;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RedisCommandResultTest {

    @Mock
    Connection connection;

    @Mock
    InputStream inputStream;

    RedisStreamReader streamReader;

    RedisCommandResult commandResult;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        streamReader = spy(new RedisStreamReader(inputStream));
        commandResult = spy(new RedisCommandResult(connection));
        doReturn(streamReader).when(connection).getStreamReader();
    }

    @Test
    public void getInt() throws ConnectionException, IOException {
        int value = 10;
        doReturn(value).when(streamReader).readInt();
        doReturn(RedisBytes.COLON_BYTE).when(streamReader).readByte();
        int actualValue = commandResult.getInt();
        assertEquals(value, actualValue);
    }

    @Test(expected = ConnectionException.class)
    public void getIntThrowsConnectionException() throws ConnectionException, IOException {
        int value = 10;
        doReturn(value).when(streamReader).readInt();
        doReturn(RedisBytes.ASTERISK_BYTE).when(streamReader).readByte();
        commandResult.getInt();
    }

    @Test
    public void getStatus() throws ConnectionException, IOException {
        String status = "OK";
        doReturn(status).when(streamReader).readString();
        doReturn(RedisBytes.PLUS_BYTE).when(streamReader).readByte();
        String actualStatus = commandResult.getStatus();
        assertEquals(status, actualStatus);
    }

    @Test
    public void getBytes() throws ConnectionException, IOException {
        int length = 3;
        byte[] bytes = { 1, 2, 3 };
        doReturn(RedisBytes.DOLLAR_BYTE).when(streamReader).readByte();
        doReturn(length).when(streamReader).readInt();
        doReturn(bytes).when(streamReader).read(length);
        byte[] actualBytes = commandResult.getBytes();
        assertArrayEquals(bytes, actualBytes);
    }

}
