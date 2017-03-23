/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 5, 2015
 */
package com.KyleDing.imcache.redis.client;

import java.io.IOException;
import java.io.OutputStream;

/**
 * The Class RedisStreamWriter.
 */
public class RedisStreamWriter {

    /** The Constant BUFFER_SIZE. */
    public static final int BUFFER_SIZE = 8192;

    /** The position. */
    int position;

    /** The buffer. */
    byte[] buffer = new byte[BUFFER_SIZE];

    /** The output stream. */
    private OutputStream outputStream;

    /**
     * Instantiates a new redis stream writer.
     *
     * @param outputStream the output stream
     */
    public RedisStreamWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Flushes output stream.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void flush() throws IOException {
        flushBuffer();
        outputStream.flush();
    }

    /**
     * Write.
     *
     * @param theByte the the byte
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void write(byte theByte) throws IOException {
        if (position == buffer.length) {
            flushBuffer();
        }
        buffer[position++] = theByte;
    }

    /**
     * Writes an int value.
     *
     * @param value the value
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void write(int value) throws IOException {
        if (value < 0) {
            write(RedisBytes.DASH_BYTE);
            value = -value;
        }
        byte[] intValue = ("" + value).getBytes("UTF-8");
        write(intValue);
    }

    /**
     * Writes a new line.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void writeNewLine() throws IOException {
        write(RedisBytes.CARRIAGE_RETURN_BYTE);
        write(RedisBytes.LINE_FEED_BYTE);
    }

    /**
     * Writes given bytes.
     *
     * @param bytes the bytes
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void write(byte[] bytes) throws IOException {
        int offset = 0;
        while (offset != bytes.length) {
            int length = bytes.length - offset;
            if (buffer.length - position < bytes.length - offset) {
                length = buffer.length - position;
            }
            write(bytes, offset, length);
            offset += length;
            position += length;
            flushBuffer();
        }
    }

    /**
     * Flushes buffer.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void flushBuffer() throws IOException {
        outputStream.write(buffer, 0, position);
        position = 0;
    }

    /**
     * Writes bytest to outputstream starting with offset and ending with offset
     * + length.
     *
     * @param bytes the bytes
     * @param offset the offset
     * @param length the length
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void write(byte[] bytes, int offset, int length) throws IOException {
        System.arraycopy(bytes, offset, buffer, position, length);
    }

}
