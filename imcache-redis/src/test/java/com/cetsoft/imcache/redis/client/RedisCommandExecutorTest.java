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
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class RedisCommandExecutorTest {

    @Mock
    Connection connection;

    @Mock
    OutputStream outputStream;

    RedisStreamWriter streamWriter;

    RedisCommandExecutor commandExecutor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        streamWriter = spy(new RedisStreamWriter(outputStream));
        commandExecutor = spy(new RedisCommandExecutor(connection));
        doReturn(streamWriter).when(connection).getStreamWriter();
    }

    @Test
    @SuppressWarnings("rawtypes")
    public void execute() throws ConnectionException, IOException {
        byte[] key = { '1' };
        byte[] value = { '1' };
        byte[][] args = { key, value };
        String expectedRedisCommandString = "*3\r\n$3\r\nSET\r\n$1\r\n1\r\n$1\r\n1\r\n";
        final byte[] outputStreamBytes = new byte[100];
        doAnswer(new Answer() {
            int position = 0;

            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                byte[] bytes = (byte[]) args[0];
                int offset = (Integer) args[1];
                int length = (Integer) args[2];
                System.arraycopy(bytes, offset, outputStreamBytes, position, length);
                position += length;
                return null;
            }
        }).when(outputStream).write((byte[]) any(), anyInt(), anyInt());

        commandExecutor.execute(RedisCommands.SET, args);

        verify(streamWriter, times(7)).writeNewLine();
        verify(streamWriter).write(RedisBytes.ASTERISK_BYTE);
        verify(streamWriter, times(3)).write(RedisBytes.DOLLAR_BYTE);
        verify(streamWriter, times(4)).write(anyInt());
        verify(streamWriter).flush();

        String actualRedisCommandString = new String(outputStreamBytes).substring(0,
                expectedRedisCommandString.length());
        assertEquals(expectedRedisCommandString, actualRedisCommandString);
    }

}
