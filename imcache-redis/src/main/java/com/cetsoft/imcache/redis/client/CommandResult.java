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
 * The Interface CommandResult returns the byte result of an executed command.
 */
public interface CommandResult {

    /**
     * Gets returned bytes of the result of an executed command.
     *
     * @return the byte[]
     * @throws ConnectionException the connection exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    byte[] getBytes() throws ConnectionException, IOException;

    /**
     * Gets the status of an executed command.
     *
     * @return the status
     * @throws ConnectionException the connection exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    String getStatus() throws ConnectionException, IOException;

    /**
     * Gets the int value returned by executed command.
     *
     * @return the int
     * @throws ConnectionException the connection exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    int getInt() throws ConnectionException, IOException;

}
