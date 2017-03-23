/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 22, 2014
 */
package com.KyleDing.imcache.offheap.bytebuffer;

import static org.junit.Assert.*;

import java.nio.BufferOverflowException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.KyleDing.imcache.offheap.bytebuffer.OffHeapByteBuffer;
import com.KyleDing.imcache.offheap.bytebuffer.OffHeapByteBufferException;
import com.KyleDing.imcache.offheap.bytebuffer.Pointer;

/**
 * The Class DirectByteBufferTest.
 */
public class OffHeapByteBufferTest {

    /** The random. */
    Random random;

    /** The buffer. */
    OffHeapByteBuffer buffer = new OffHeapByteBuffer(0, 1024 * 1024 * 10);

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        random = new Random();
    }

    /**
     * Store.
     */
    @Test
    public void store() {
        int size = 100;
        byte[] expectedBytes = new byte[size];
        random.nextBytes(expectedBytes);
        Pointer pointer = buffer.store(expectedBytes);
        byte[] actualBytes = buffer.retrieve(pointer);
        assertArrayEquals(expectedBytes, actualBytes);
    }

    /**
     * Allocate.
     */
    @Test(expected = BufferOverflowException.class)
    public void allocate() {
        int size = 11 * 1024 * 1024;
        byte[] expectedBytes = new byte[size];
        random.nextBytes(expectedBytes);
        buffer.allocate(expectedBytes);
    }

    /**
     * Update.
     */
    @Test
    public void update() {
        int size = 100;
        byte[] bytes = new byte[size];
        random.nextBytes(bytes);
        Pointer pointer = buffer.store(bytes);
        byte[] expectedBytes = new byte[size];
        random.nextBytes(expectedBytes);
        pointer = buffer.update(pointer, expectedBytes);
        byte[] actualBytes = buffer.retrieve(pointer);
        assertArrayEquals(expectedBytes, actualBytes);
    }

    /**
     * Update greater ex payload.
     */
    @Test
    public void updateGreaterExPayload() {
        int size = 100;
        byte[] bytes = new byte[size];
        random.nextBytes(bytes);
        Pointer pointer = buffer.store(bytes);
        byte[] expectedBytes = new byte[size + 5];
        random.nextBytes(expectedBytes);
        pointer = buffer.update(pointer, expectedBytes);
        byte[] actualBytes = buffer.retrieve(pointer);
        assertArrayEquals(expectedBytes, actualBytes);
    }

    /**
     * Header dirty.
     */
    @Test(expected = OffHeapByteBufferException.class)
    public void headerDirty() {
        int size = 5;
        byte[] bytes = new byte[size];
        bytes[0] = -1;
        random.nextBytes(bytes);
        buffer.header(bytes);
    }

    /**
     * Header free.
     */
    @Test(expected = OffHeapByteBufferException.class)
    public void headerFree() {
        int size = 5;
        byte[] bytes = new byte[size];
        bytes[0] = 0;
        random.nextBytes(bytes);
        buffer.header(bytes);
    }

    /**
     * Header used.
     */
    @Test(expected = OffHeapByteBufferException.class)
    public void headerUsed() {
        int size = 5;
        byte[] bytes = new byte[size];
        bytes[0] = 1;
        random.nextBytes(bytes);
        buffer.header(bytes);
    }

    /**
     * Removes the.
     */
    @Test
    public void remove() {
        int size = 100;
        byte[] expectedBytes = new byte[size];
        random.nextBytes(expectedBytes);
        Pointer pointer = buffer.store(expectedBytes);
        byte[] actualBytes = buffer.remove(pointer);
        assertArrayEquals(expectedBytes, actualBytes);
    }

}
