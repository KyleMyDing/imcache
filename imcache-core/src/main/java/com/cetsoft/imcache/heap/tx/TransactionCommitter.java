/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 23, 2013
 */
package com.KyleDing.imcache.heap.tx;

/**
 * The Interface TransactionCommitter allows transactions to be applied to the
 * target resource.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public interface TransactionCommitter<K, V> {

    /**
     * Do put.
     *
     * @param key the key
     * @param value the value
     */
    void doPut(K key, V value);
}
