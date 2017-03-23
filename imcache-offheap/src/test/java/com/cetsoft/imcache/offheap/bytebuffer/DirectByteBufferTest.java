/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 22, 2014
 */
package com.KyleDing.imcache.offheap.bytebuffer;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.KyleDing.imcache.offheap.bytebuffer.DirectByteBuffer;

/**
 * The Class DirectByteBufferTest.
 */
public class DirectByteBufferTest {

    /** The random. */
    Random random;

    /** The buffer. */
    DirectByteBuffer buffer = new DirectByteBuffer(1024 * 1024 * 10);

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        random = new Random();
    }

    /**
     * Put.
     */
    @Test
    public void put() {
        int size = 100;
        byte[] expectedBytes = new byte[size];
        random.nextBytes(expectedBytes);
        buffer.put(0, expectedBytes, 0, expectedBytes.length);
        byte[] actualBytes = new byte[size];
        buffer.get(0, actualBytes, 0, actualBytes.length);
        assertArrayEquals(expectedBytes, actualBytes);
    }

    /**
     * Put length greater than threshold.
     */
    @Test
    public void putLengthGreaterThanThreshold() {
        int size = 1024 * 1024 * 2;
        byte[] expectedBytes = new byte[size];
        random.nextBytes(expectedBytes);
        buffer.put(0, expectedBytes, 0, expectedBytes.length);
        byte[] actualBytes = new byte[size];
        buffer.get(0, actualBytes, 0, actualBytes.length);
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    public void free() {
        int size = 1024 * 1024 * 2;
        byte[] bytes = new byte[size];
        random.nextBytes(bytes);
        buffer.free();
    }
}
