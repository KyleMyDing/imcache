/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jun 5, 2014
 */
package com.KyleDing.imcache.cache.async;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * The Class ConcurrentEvictionListenerTest.
 */
public class ConcurrentEvictionListenerTest {

    /** The cache task. */
    @Mock
    CacheTask<Object, Object> cacheTask;

    /** The cache tasks. */
    @Mock
    BlockingQueue<CacheTask<Object, Object>> cacheTasks;

    /** The async eviction listener. */
    ConcurrentEvictionListener<Object, Object> asyncEvictionListener;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        asyncEvictionListener = spy(new ConcurrentEvictionListener<Object, Object>() {
            @Override
            public void save(List<CacheTask<Object, Object>> cacheTasks) {
            }
        });
        asyncEvictionListener.cacheTasks = cacheTasks;
    }

    /**
     * Inits the.
     *
     * @throws InterruptedException the interrupted exception
     */
    @Test
    public void init() throws InterruptedException {
        doNothing().when(asyncEvictionListener).drainQueue();
        asyncEvictionListener.init(1000, 3);
        Thread.sleep(5);
        verify(asyncEvictionListener, atLeast(1)).drainQueue();
    }

    /**
     * Drain queue.
     */
    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void drainQueue() {
        final TasksHolder tasksHolder = new TasksHolder();
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws Throwable {
                List<CacheTask<Object, Object>> tasks = (List<CacheTask<Object, Object>>) invocation.getArguments()[0];
                tasks.add(cacheTask);
                tasksHolder.tasks = tasks;
                return null;
            }
        }).when(cacheTasks).drainTo(anyList(), anyInt());
        doNothing().when(asyncEvictionListener).save(anyList());
        asyncEvictionListener.drainQueue();
        assertEquals(cacheTask, tasksHolder.tasks.get(0));
    }

    /**
     * The Class TasksHolder.
     */
    private static class TasksHolder {

        /** The tasks. */
        List<CacheTask<Object, Object>> tasks;
    }

}
