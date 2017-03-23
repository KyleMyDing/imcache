/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 25, 2014
 */
package com.KyleDing.imcache.cache.search.index;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.KyleDing.imcache.cache.search.index.UniqueHashIndex;

public class UniqueHashIndexTest {

    /** The index base. */
    UniqueHashIndex index;

    @Mock
    Map<Object, Object> map;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        index = spy(new UniqueHashIndex());
        index.map = map;
    }

    @Test
    public void put() {
        Object object = new Object();
        doReturn(null).when(map).put(object, object);
        index.put(object, object);
        verify(map).put(object, object);
    }

    @Test
    public void remove() {
        Object object = new Object();
        doReturn(true).when(map).remove(object);
        index.remove(object, object);
        verify(map).remove(object);
    }

    @Test
    public void equalsTo() {
        Object object = new Object();
        doReturn(object).when(map).get(object);
        List<Object> actualObjects = index.equalsTo(object);
        assertEquals(object, actualObjects.get(0));
    }

}
