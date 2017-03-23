/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 23, 2013
 */
package com.KyleDing.imcache.serialization;

/**
 * The Interface Serializer.
 *
 * @param <V> the value type
 */
public interface Serializer<V> {

    /**
     * Serialize.
     *
     * @param value the value
     * @return the byte[]
     */
    byte[] serialize(V value);

    /**
     * Deserialize.
     *
     * @param payload the payload
     * @return the v
     */
    V deserialize(byte[] payload);
}