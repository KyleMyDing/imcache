/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jan 16, 2014
 */
package com.KyleDing.imcache.serialization;

import java.util.ArrayList;
import java.util.List;

import com.KyleDing.imcache.cache.CollectionItem;
import com.KyleDing.imcache.cache.util.SerializationUtils;

/**
 * The Class CollectionSerializer.
 *
 * @param <V> the value type
 */
public class CollectionSerializer<V> implements Serializer<CollectionItem<V>> {

    /** The Constant OBJECT_SIZE. */
    private static final int OBJECT_SIZE = 68;

    /** The serializer. */
    private Serializer<V> serializer;

    /**
     * Instantiates a new collection serializer.
     *
     * @param serializer the serializer
     */
    public CollectionSerializer(Serializer<V> serializer) {
        this.serializer = serializer;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.KyleDing.imcache.serialization.Serializer#serialize(java.lang.Object)
     */
    public byte[] serialize(CollectionItem<V> value) {
        List<Byte> bytes = new ArrayList<Byte>(value.getValue().size() * OBJECT_SIZE);
        for (V v : value.getValue()) {
            byte[] currentPayload = serializer.serialize(v);
            for (byte lengthByte : SerializationUtils.serializeInt(currentPayload.length)) {
                bytes.add(lengthByte);
            }
            for (int i = 0; i < currentPayload.length; i++) {
                bytes.add(currentPayload[i]);
            }
        }
        byte[] payload = new byte[bytes.size()];
        for (int i = 0; i < payload.length; i++) {
            payload[i] = bytes.get(i);
        }
        return payload;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.serialization.Serializer#deserialize(byte[])
     */
    public CollectionItem<V> deserialize(byte[] payload) {
        int pos = 0;
        List<V> values = new ArrayList<V>();
        while (pos < payload.length) {
            byte[] lengthBytes = new byte[4];
            System.arraycopy(payload, pos, lengthBytes, 0, 4);
            int length = SerializationUtils.deserializeInt(lengthBytes);
            pos += 4;
            byte[] bytes = new byte[length];
            System.arraycopy(payload, pos, bytes, 0, length);
            pos += length;
            V v = serializer.deserialize(bytes);
            values.add(v);
        }
        return new CollectionItem<V>(values);
    }

}