/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 22, 2013
 */
package com.KyleDing.imcache.offheap.bytebuffer;

/**
 * The Interface OffHeapStore.
 */
public interface OffHeapStore {

    /**
     * Retrieves the payload associated with the pointer.
     *
     * @param pointer the pointer
     * @return the byte[]
     */
    byte[] retrieve(Pointer pointer);

    /**
     * Removes the payload and marks it as dirty.
     *
     * @param pointer the pointer
     * @return the byte[]
     */
    byte[] remove(Pointer pointer);

    /**
     * Stores the payload.
     *
     * @param payload the payload
     * @return the pointer
     */
    Pointer store(byte[] payload);

    /**
     * Updates the payload by marking exPayload as dirty.
     *
     * @param pointer the pointer
     * @param payload the payload
     * @return the pointer
     */
    Pointer update(Pointer pointer, byte[] payload);

    /**
     * Calculates and returns Dirty memory.
     *
     * @return the long
     */
    long dirtyMemory();

    /**
     * Calculates and returns Used memory.
     *
     * @return the long
     */
    long usedMemory();

    /**
     * Calculates and returns Free memory.
     *
     * @return the long
     */
    long freeMemory();

    /**
     * Frees the memory.
     */
    void free();
}
