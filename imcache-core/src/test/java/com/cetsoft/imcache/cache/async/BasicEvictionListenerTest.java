/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jun 5, 2014
 */
package com.KyleDing.imcache.cache.async;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

/**
 * The Class BasicEvictionListenerTest.
 */
public class BasicEvictionListenerTest {

    /** The async eviction listener. */
    BasicEvictionListener<Object, Object> asyncEvictionListener;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        asyncEvictionListener = spy(new BasicEvictionListener<Object, Object>() {
            public void save(Object key, Object value) {
            }
        });
    }

    /**
     * On eviction.
     */
    @Test
    public void onEviction() {
        Object key = new Object(), value = new Object();
        doNothing().when(asyncEvictionListener).save(key, value);
        asyncEvictionListener.onEviction(key, value);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        verify(asyncEvictionListener).save(key, value);
    }

}
