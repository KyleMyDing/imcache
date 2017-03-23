/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 3, 2015
 */
package com.KyleDing.imcache.cache.builder;

import java.util.List;

import com.KyleDing.imcache.cache.search.IndexHandler;
import com.KyleDing.imcache.cache.search.Query;
import com.KyleDing.imcache.cache.search.index.IndexType;

public class DummyIndexHandler implements IndexHandler<Object, Object> {

    public static DummyIndexHandler getInstance() {
        return new DummyIndexHandler();
    }

    @Override
    public void addIndex(String attributeName, IndexType type) {
    }

    @Override
    public void add(Object key, Object value) {
    }

    @Override
    public void remove(Object key, Object value) {
    }

    @Override
    public void clear() {
    }

    @Override
    public List<Object> execute(Query query) {
        return null;
    }

}
