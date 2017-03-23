/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 22, 2013
 */
package com.KyleDing.imcache.offheap.bytebuffer;

/**
 * The Class OffHeapByteBufferException.
 */
public class OffHeapByteBufferException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5414061921999468983L;

    /**
     * Instantiates a new off heap byte buffer exception.
     *
     * @param string the string
     */
    public OffHeapByteBufferException(String string) {
        super(string);
    }

}
