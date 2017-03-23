/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 7, 2015
 */
package com.KyleDing.imcache.redis.client;

import java.io.UnsupportedEncodingException;

/**
 * The Enum RedisCommands are enum values of corresponding redis commands.
 */
public enum RedisCommands implements ByteCommand {

    PING, SET, GET, EXPIRE, FLUSHDB, DBSIZE;

    public static final String CHARSET = "UTF-8";

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.redis.client.ByteCommand#getBytes()
     */
    public byte[] getBytes() {
        try {
            return this.name().getBytes(CHARSET);
        } catch (UnsupportedEncodingException e) {
            //do nothing.
        }
        return null;
    }
}
