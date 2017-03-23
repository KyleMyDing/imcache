/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 14, 2015
 */
package com.KyleDing.imcache.redis;

/**
 * The Class RedisCacheException.
 */
public class RedisCacheException extends RuntimeException {

    /**
     * Instantiates a new redis cache exception.
     *
     * @param exception the exception
     */
    public RedisCacheException(Exception exception) {
        super(exception);
    }

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5916280479309274446L;

}
