/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 2, 2014
 */
/***********************************************************************
 * Copyright 2010 Google Inc. All Rights Reserved.
 *
 ************************************************************************/
package com.KyleDing.imcache.concurrent;

/**
 * A class that can determine the weight of a value. The total weight threshold
 * is used to determine when an eviction is required.
 */
public interface Weigher<V> {

    /**
     * Measures an object's weight to determine how many units of capacity that
     * the value consumes. A value must consume a minimum of one unit.
     *
     * @param value the object to weigh
     * @return the object's weight
     */
    int weightOf(V value);
}
