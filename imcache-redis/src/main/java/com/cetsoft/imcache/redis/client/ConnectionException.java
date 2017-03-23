/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 5, 2015
 */
package com.KyleDing.imcache.redis.client;

import java.io.IOException;

/**
 * The Class ConnectionException.
 */
public class ConnectionException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6640803324694125408L;

    /**
     * Instantiates a new connection exception.
     *
     * @param ex the ex
     */
    public ConnectionException(IOException ex) {
        super(ex);
    }

    /**
     * Instantiates a new connection exception.
     *
     * @param string the string
     */
    public ConnectionException(String string) {
        super(string);
    }

}
