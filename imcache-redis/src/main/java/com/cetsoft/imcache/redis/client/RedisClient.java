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
 * The Class RedisClient.
 */
public class RedisClient implements Client {

    /** The Constant STATUS_OK. */
    private static final String STATUS_OK = "OK";

    /** The command result. */
    CommandResult commandResult;

    /** The command executor. */
    CommandExecutor commandExecutor;

    Transaction transaction = new RedisTransaction();

    /**
     * Instantiates a new redis client.
     *
     * @param connection the connection
     */
    public RedisClient(Connection connection) {
        this.commandExecutor = new RedisCommandExecutor(connection);
        this.commandResult = new RedisCommandResult(connection);
    }

    /**
     * Instantiates a new redis client.
     */
    public RedisClient() {
        this(new Connection());
    }

    /**
     * Instantiates a new redis client.
     *
     * @param host the host
     */
    public RedisClient(final String host) {
        this(new Connection(host));
    }

    /**
     * Instantiates a new redis client.
     *
     * @param host the host
     * @param port the port
     */
    public RedisClient(final String host, final int port) {
        this(new Connection(host, port));
    }

    /**
     * Runs a void command.
     *
     * @param command the command
     * @param args the args
     * @throws ConnectionException the connection exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void runVoidCommand(ByteCommand command, byte[]... args) throws ConnectionException, IOException {
        transaction.open();
        try {
            commandExecutor.execute(command, args);
            String status = commandResult.getStatus();
            if (!status.equals(STATUS_OK)) {
                throw new ConnectionException("Command couldn't run successfully " + command.toString());
            }
        } finally {
            transaction.close();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.redis.client.Client#ping()
     */
    @Override
    public void ping() throws ConnectionException, IOException {
        runVoidCommand(RedisCommands.PING);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.redis.client.Client#set(byte[], byte[])
     */
    @Override
    public void set(byte[] key, byte[] value) throws ConnectionException, IOException {
        runVoidCommand(RedisCommands.SET, key, value);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.redis.client.Client#get(byte[])
     */
    @Override
    public byte[] get(byte[] key) throws ConnectionException, IOException {
        transaction.open();
        try {
            commandExecutor.execute(RedisCommands.GET, key);
            return commandResult.getBytes();
        } finally {
            transaction.close();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.redis.client.Client#expire(byte[])
     */
    @Override
    public byte[] expire(byte[] key) throws ConnectionException, IOException {
        transaction.open();
        try {
            byte[] value = get(key);
            commandExecutor.execute(RedisCommands.EXPIRE, key, new byte[] { '0' });
            commandResult.getInt();
            return value;
        } finally {
            transaction.close();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.redis.client.Client#flushdb()
     */
    @Override
    public void flushdb() throws ConnectionException, IOException {
        runVoidCommand(RedisCommands.FLUSHDB);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.redis.client.Client#dbsize()
     */
    @Override
    public int dbsize() throws ConnectionException, IOException {
        transaction.open();
        try {
            commandExecutor.execute(RedisCommands.DBSIZE);
            return commandResult.getInt();
        } finally {
            transaction.close();
        }
    }

}
