/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Jul 10, 2015
 */
package com.KyleDing.imcache.cache.util;

/**
 * The Class ThreadUtils.
 */
public class ThreadUtils {

    /**
     * Creates a daemon thread.
     *
     * @param runnable the runnable
     * @param threadName the thread name
     * @return the thread
     */
    public static Thread createDaemonThread(Runnable runnable, String threadName) {
        Thread daemonThread = new Thread(runnable, threadName);
        daemonThread.setDaemon(true);
        return daemonThread;
    }
}
