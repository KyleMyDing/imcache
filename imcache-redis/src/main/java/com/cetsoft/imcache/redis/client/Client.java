/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 7, 2015
 */
package com.KyleDing.imcache.redis.client;

import java.io.IOException;

/**
 * The Interface Client provides redis commands. Methods in this interface have
 * corresponding redis command with the same name. This class performs
 * operations by the help of Connection and RedisInputStream and
 * RedisOutputStream.
 */
public interface Client {

    /**
     * Ping the server. This command is often used to test if a connection is
     * still alive.
     *
     * @throws ConnectionException
     * @throws IOException
     */
    void ping() throws ConnectionException, IOException;

    /**
     * Set key to hold the value. If key already holds a value, it is
     * overwritten.
     *
     * @param key the key
     * @param value the value
     * @throws IOException
     * @throws ConnectionException
     */
    void set(byte[] key, byte[] value) throws ConnectionException, IOException;

    /**
     * Get the value of key. If the key does not exist the special value null is
     * returned.
     *
     * @param key the key
     * @return the byte[]
     * @throws IOException
     * @throws ConnectionException
     */
    byte[] get(byte[] key) throws ConnectionException, IOException;

    /**
     * Set a timeout on key. After the timeout has expired, the key will
     * automatically be deleted.
     *
     * @param key the key
     * @return the byte[]
     * @throws IOException
     * @throws ConnectionException
     */
    byte[] expire(byte[] key) throws ConnectionException, IOException;

    /**
     * Delete all the keys of the currently selected DB.
     *
     * @throws IOException
     * @throws ConnectionException
     */
    void flushdb() throws ConnectionException, IOException;

    /**
     * Return the number of keys.
     *
     * @return the int
     * @throws IOException
     * @throws ConnectionException
     */
    int dbsize() throws ConnectionException, IOException;

}
