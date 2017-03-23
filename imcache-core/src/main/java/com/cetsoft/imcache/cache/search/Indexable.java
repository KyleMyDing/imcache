/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Sep 28, 2013
 */
package com.KyleDing.imcache.cache.search;

import com.KyleDing.imcache.cache.search.index.IndexType;

/**
 * The Interface Indexable for receiving indexes. The class that is interested
 * in processing indexes implements this interface. When an index is added, it
 * provides used to quickly and efficiently provide the exact location of the
 * corresponding data.
 */
public interface Indexable {

    /**
     * Adds the index.
     *
     * @param attributeName the attribute name
     * @param type the type
     */
    void addIndex(String attributeName, IndexType type);
}
