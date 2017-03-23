/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jun 2, 2014
 */
package com.KyleDing.imcache.cache.serialization;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.KyleDing.imcache.cache.CollectionItem;
import com.KyleDing.imcache.cache.util.SerializationUtils;
import com.KyleDing.imcache.serialization.CollectionSerializer;
import com.KyleDing.imcache.serialization.Serializer;

/**
 * The Class DiffCriteriaTest.
 */
public class CollectionSerializerTest {

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Serialize and deserialize.
     */
    @Test
    public void serializeAndDeserialize() {
        List<Integer> objects = new ArrayList<Integer>();
        objects.add(0);
        objects.add(1);
        objects.add(2);
        CollectionSerializer<Integer> collectionSerializer = new CollectionSerializer<Integer>(
                new Serializer<Integer>() {
                    public byte[] serialize(Integer value) {
                        return SerializationUtils.serializeInt(value);
                    }

                    public Integer deserialize(byte[] payload) {
                        return SerializationUtils.deserializeInt(payload);
                    }
                });
        CollectionItem<Integer> collectionItem = new CollectionItem<Integer>(objects);
        byte[] payload = collectionSerializer.serialize(collectionItem);
        CollectionItem<Integer> actualCollectionItem = collectionSerializer.deserialize(payload);
        assertTrue(actualCollectionItem.getValue().contains(0));
        assertTrue(actualCollectionItem.getValue().contains(1));
        assertTrue(actualCollectionItem.getValue().contains(2));
    }

}
