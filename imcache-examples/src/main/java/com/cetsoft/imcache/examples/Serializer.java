/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 23, 2013
 */
package com.KyleDing.imcache.examples;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * The Class Serializer.
 */
public class Serializer {

    /**
     * Serialize.
     *
     * @param object the object
     * @return the byte[]
     */
    public static byte[] serialize(Object object) {
        byte[] objectBytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(object);
            objectBytes = bos.toByteArray();
            out.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objectBytes;
    }

    /**
     * Deserialize.
     *
     * @param <C> the generic type
     * @param bytes the bytes
     * @return the c
     */
    @SuppressWarnings("unchecked")
    public static <C> C deserialize(byte[] bytes) {
        Object object = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try {
            ObjectInput in = new ObjectInputStream(bis);
            object = in.readObject();
            bis.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (C) object;
    }
}
