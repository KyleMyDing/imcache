/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 7, 2015
 */
package com.KyleDing.imcache.redis.client;

/**
 * The Interface ByteCommand represent a byte command to be sent to redis
 * server.
 */
public interface ByteCommand {

    /**
     * Gets the bytes associated with the command.
     *
     * @return the bytes
     */
    byte[] getBytes();
}
