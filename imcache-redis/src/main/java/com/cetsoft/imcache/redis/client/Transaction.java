/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 17, 2015
 */
package com.KyleDing.imcache.redis.client;

/**
 * The Interface Transaction provides monitor for a resource between threads. If
 * a thread opens a transaction, any subsequent request to open will wait until
 * the thread closes transaction. For example: byte[] read(){
 * transaction.open(); try{ byte[] bytes = new byte[1024];
 * inputStream.read(bytes); return bytes; }finally{ transaction.close(); } } In
 * this example, no other can read from outputStream until thread closes
 * transaction.
 */
public interface Transaction {

    /**
     * Opens a new transaction.
     */
    void open();

    /**
     * Closes a new transaction.
     */
    void close();

}
