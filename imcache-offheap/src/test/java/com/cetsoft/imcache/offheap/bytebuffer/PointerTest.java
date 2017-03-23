/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 4, 2015
 */
package com.KyleDing.imcache.offheap.bytebuffer;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mock;

import com.KyleDing.imcache.offheap.bytebuffer.OffHeapByteBuffer;
import com.KyleDing.imcache.offheap.bytebuffer.Pointer;

public class PointerTest {

    @Mock
    OffHeapByteBuffer offHeapByteBuffer;

    @Test
    public void copy() {
        int position = 100;
        long accessTime = 1212;
        Pointer pointer = new Pointer(0, 0, null);
        Pointer pointerToCopy = new Pointer(0, 0, null);
        pointerToCopy.setAccessTime(accessTime);
        pointerToCopy.setPosition(position);
        pointerToCopy.setOffHeapByteBuffer(offHeapByteBuffer);
        pointer.copy(pointerToCopy);
        assertEquals(position, pointer.getPosition());
        assertEquals(accessTime, pointer.getAccessTime());
        assertEquals(offHeapByteBuffer, pointer.getOffHeapByteBuffer());
    }
}
