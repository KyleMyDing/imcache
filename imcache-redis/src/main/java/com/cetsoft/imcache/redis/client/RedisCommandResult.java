/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 8, 2015
 */
package com.KyleDing.imcache.redis.client;

import java.io.IOException;

/**
 * The Class RedisCommandResult.
 */
public class RedisCommandResult implements CommandResult {

    /** The connection. */
    Connection connection;

    /**
     * Instantiates a new redis command result.
     *
     * @param connection the connection
     */
    public RedisCommandResult(Connection connection) {
        this.connection = connection;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.redis.client.CommandResult#getBytes()
     */
    @Override
    public byte[] getBytes() throws ConnectionException, IOException {
        RedisStreamReader streamReader = getStreamReader();
        checkMessageType(streamReader, RedisBytes.DOLLAR_BYTE);
        int length = streamReader.readInt();
        return streamReader.read(length);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.redis.client.CommandResult#getStatus()
     */
    @Override
    public String getStatus() throws ConnectionException, IOException {
        RedisStreamReader streamReader = getStreamReader();
        checkMessageType(streamReader, RedisBytes.PLUS_BYTE);
        return streamReader.readString();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.redis.client.CommandResult#getInt()
     */
    @Override
    public int getInt() throws ConnectionException, IOException {
        RedisStreamReader streamReader = getStreamReader();
        checkMessageType(streamReader, RedisBytes.COLON_BYTE);
        return streamReader.readInt();
    }

    /**
     * Checks message type received. If it's unexpected throws an exception.
     *
     * @param streamReader the stream reader
     * @param expectedByte the expected byte
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ConnectionException the connection exception
     */
    protected void checkMessageType(RedisStreamReader streamReader, byte expectedByte) throws IOException,
            ConnectionException {
        final byte actualByte = streamReader.readByte();
        if (actualByte != expectedByte) {
            throw new ConnectionException("Expected(" + ((char) expectedByte) + "), Found(" + ((char) actualByte)
                    + ").");
        }
    }

    /**
     * Gets the stream reader.
     *
     * @return the input stream
     * @throws ConnectionException the connection exception
     */
    protected RedisStreamReader getStreamReader() throws ConnectionException {
        connection.open();
        return connection.getStreamReader();
    }

}
