/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jun 5, 2014
 */
package com.KyleDing.imcache.cache.async;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.KyleDing.imcache.cache.util.ThreadUtils;

/**
 * The scheduled eviction listener interface for receiving eviction events. This
 * class drains cache task queue and executes saveAll function in a scheduled
 * manner.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public abstract class ScheduledEvictionListener<K, V> extends QueuingEvictionListener<K, V> {

    /** The Constant DEFAULT_PERIOD. */
    public static final long DEFAULT_PERIOD = 3000;

    /** The Constant NO_OF_EVICTION_DRAINERS. */
    private static final AtomicInteger NO_OF_EVICTION_DRAINERS = new AtomicInteger();

    /**
     * Instantiates a new scheduled eviction listener.
     */
    public ScheduledEvictionListener() {
        this(DEFAULT_BATCH_SIZE, DEFAULT_PERIOD, DEFAULT_QUEUE_SIZE);
    }

    /**
     * Instantiates a new scheduled eviction listener.
     *
     * @param batchSize the batch size
     * @param period the period
     * @param queueSize the queue size
     */
    public ScheduledEvictionListener(int batchSize, long period, int queueSize) {
        this.batchSize = batchSize;
        init(period, queueSize);
    }

    /**
     * Inits the.
     *
     * @param period the period
     * @param queueSize the queue size
     */
    protected void init(long period, int queueSize) {
        cacheTasks = new ArrayBlockingQueue<CacheTask<K, V>>(queueSize);
        ScheduledExecutorService drainerService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                return ThreadUtils.createDaemonThread(runnable, "imcache:batchAsyncEvictionDrainer(thread="
                        + NO_OF_EVICTION_DRAINERS.incrementAndGet() + ")");
            }
        });
        drainerService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                drainQueue();
            }
        }, period, period, TimeUnit.MILLISECONDS);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.cache.async.QueuingEvictionListener#drainQueue()
     */
    @Override
    protected void drainQueue() {
        while (!cacheTasks.isEmpty()) {
            super.drainQueue();
        }
    }

}
