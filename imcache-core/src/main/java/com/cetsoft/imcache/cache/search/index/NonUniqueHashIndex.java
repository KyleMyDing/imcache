/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 29, 2013
 */
package com.KyleDing.imcache.cache.search.index;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The Class NonUniqueHashIndex is type of index where hash indexed value can
 * have one or more corresponding values.
 */
public class NonUniqueHashIndex extends MultiValueIndex {

    /**
     * Instantiates a new non unique hash index.
     */
    public NonUniqueHashIndex() {
        this.map = new ConcurrentHashMap<Object, Set<Object>>();
    }
}
