/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 20, 2014
 */
package com.KyleDing.imcache.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.KyleDing.imcache.cache.search.filter.Filter;

/**
 * The Class CollectionItem is a list of CacheItem.
 *
 * @param <V> the value type
 */
public class CollectionItem<V> implements CacheItem<Collection<V>> {

    /** The objects. */
    private List<? extends V> objects;

    /**
     * Instantiates a new collection item.
     *
     * @param objects the objects
     */
    public CollectionItem(Collection<? extends V> objects) {
        this.objects = new ArrayList<V>(objects);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.KyleDing.imcache.cache.CacheItem#getValue()
     */
    @SuppressWarnings("unchecked")
    public Collection<V> getValue() {
        return (Collection<V>) objects;
    }

    /**
     * Filter.
     *
     * @param filter the filter
     * @return the collection
     */
    @SuppressWarnings("unchecked")
    public Collection<V> filter(Filter filter) {
        List<Object> filteredObjects = filter.filter((List<Object>) objects);
        return (Collection<V>) filteredObjects;
    }

}
