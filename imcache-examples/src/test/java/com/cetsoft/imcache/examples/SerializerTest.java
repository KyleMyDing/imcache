/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 4, 2015
 */
package com.KyleDing.imcache.examples;

import org.junit.Test;
import static org.junit.Assert.*;

public class SerializerTest {

    @Test
    public void serializeDeserialize() {
        Integer item = new Integer(1);
        byte[] serializedItem = Serializer.serialize(item);
        Integer deserializeItem = Serializer.deserialize(serializedItem);
        assertEquals(item, deserializeItem);
    }
}
