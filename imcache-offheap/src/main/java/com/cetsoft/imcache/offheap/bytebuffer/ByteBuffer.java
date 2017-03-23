/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 22, 2013
 */
package com.KyleDing.imcache.offheap.bytebuffer;

/**
 * The Interface ByteBuffer.
 */
public interface ByteBuffer {

    /**
     * Gets the destination from the specified location.
     *
     * @param position the position
     * @param destination the destination
     * @param offset the offset
     * @param length the length
     */
    void get(int position, byte[] destination, int offset, int length);

    /**
     * Puts source to the specified location of the ByteBuffer.
     *
     * @param position the position
     * @param source the source
     * @param offset the offset
     * @param length the length
     */
    void put(int position, byte[] source, int offset, int length);

    /**
     * Frees the buffer.
     */
    void free();
}
