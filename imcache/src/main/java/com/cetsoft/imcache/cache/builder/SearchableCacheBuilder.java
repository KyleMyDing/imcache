/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 17, 2015
 */
package com.KyleDing.imcache.cache.builder;

import java.util.List;

import com.KyleDing.imcache.cache.search.ConcurrentIndexHandler;
import com.KyleDing.imcache.cache.search.IndexHandler;
import com.KyleDing.imcache.cache.search.Query;
import com.KyleDing.imcache.cache.search.index.IndexType;

public abstract class SearchableCacheBuilder extends AbstractCacheBuilder {

    /** The is searchable. */
    protected volatile boolean isSearchable = false;

    /** The query executer. */
    protected IndexHandler<Object, Object> indexHandler;

    /** The Constant QUERY_EXECUTER. */
    protected static final IndexHandler<Object, Object> QUERY_EXECUTER = new IndexHandler<Object, Object>() {
        public void addIndex(String attributeName, IndexType type) {
        }

        public void remove(Object key, Object value) {
        }

        public List<Object> execute(Query query) {
            return null;
        }

        public void clear() {
        }

        public void add(Object key, Object value) {
        }
    };

    /**
     * Instantiates a new searchable cache builder.
     */
    protected SearchableCacheBuilder() {
        super();
        indexHandler = QUERY_EXECUTER;
    }

    /**
     * Searchable.
     */
    protected void searchable() {
        if (!isSearchable) {
            indexHandler = new ConcurrentIndexHandler<Object, Object>();
            isSearchable = true;
        }
    }
}
