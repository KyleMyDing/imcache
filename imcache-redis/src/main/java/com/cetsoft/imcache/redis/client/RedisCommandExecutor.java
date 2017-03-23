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
 * The Class RedisCommandExecutor.
 */
public class RedisCommandExecutor implements CommandExecutor {

    /** The connection. */
    Connection connection;

    /**
     * Instantiates a new redis command executor.
     *
     * @param connection the connection
     */
    public RedisCommandExecutor(Connection connection) {
        this.connection = connection;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.redis.client.CommandExecutor#execute(com.KyleDing.
     * imcache.redis.client.ByteCommand, byte[][])
     */
    public void execute(final ByteCommand command, final byte[]... args) throws ConnectionException, IOException {
        connection.open();
        RedisStreamWriter streamWriter = connection.getStreamWriter();

        streamWriter.write(RedisBytes.ASTERISK_BYTE);
        streamWriter.write(args.length + 1);// 1 comes from set
        streamWriter.writeNewLine();
        streamWriter.write(RedisBytes.DOLLAR_BYTE);
        streamWriter.write(command.getBytes().length);
        streamWriter.writeNewLine();
        streamWriter.write(command.getBytes());
        streamWriter.writeNewLine();

        for (final byte[] arg : args) {
            streamWriter.write(RedisBytes.DOLLAR_BYTE);
            streamWriter.write(arg.length);
            streamWriter.writeNewLine();
            streamWriter.write(arg);
            streamWriter.writeNewLine();
        }
        streamWriter.flush();
    }

}
