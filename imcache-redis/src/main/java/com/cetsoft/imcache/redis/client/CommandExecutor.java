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
 * The Interface CommandExecutor is responsible for executing given byte
 * command. It throws ConnectionException in case of a connection failure.
 */
public interface CommandExecutor {

    /**
     * Executes the given byte command.
     *
     * @param command the command
     * @param args the args
     * @throws ConnectionException the connection exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    void execute(final ByteCommand command, final byte[]... args) throws ConnectionException, IOException;
}
