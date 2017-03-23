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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A common set of {@link Weigher} implementations.
 *
 * @author ben.manes@gmail.com (Ben Manes)
 * @see <a href="http://code.google.com/p/concurrentlinkedhashmap/">
 *      http://code.google.com/p/concurrentlinkedhashmap/</a>
 */
public final class Weighers {

    private Weighers() {
        throw new AssertionError();
    }

    /**
     * A weigher where a value has a weight of <tt>1</tt>. A map bounded with
     * this weigher will evict when the number of key-value pairs exceeds the
     * capacity.
     *
     * @return A weigher where a value takes one unit of capacity.
     */
    @SuppressWarnings({ "unchecked" })
    public static <V> Weigher<V> singleton() {
        return (Weigher<V>) SingletonWeigher.INSTANCE;
    }

    /**
     * A weigher where the value is a byte array and its weight is the number of
     * bytes. A map bounded with this weigher will evict when the number of
     * bytes exceeds the capacity rather than the number of key-value pairs in
     * the map. This allows for restricting the capacity based on the
     * memory-consumption and is primarily for usage by dedicated caching
     * servers that hold the serialized data.
     * <p>
     * A value with a weight of <tt>0</tt> will be rejected by the map. If a
     * value with this weight can occur then the caller should eagerly evaluate
     * the value and treat it as a removal operation. Alternatively, a custom
     * weigher may be specified on the map to assign an empty value a positive
     * weight.
     *
     * @return A weigher where each byte takes one unit of capacity.
     */
    public static Weigher<byte[]> byteArray() {
        return ByteArrayWeigher.INSTANCE;
    }

    /**
     * A weigher where the value is a {@link Iterable} and its weight is the
     * number of elements. This weigher only should be used when the alternative
     * {@link #collection()} weigher cannot be, as evaluation takes O(n) time. A
     * map bounded with this weigher will evict when the total number of
     * elements exceeds the capacity rather than the number of key-value pairs
     * in the map.
     * <p>
     * A value with a weight of <tt>0</tt> will be rejected by the map. If a
     * value with this weight can occur then the caller should eagerly evaluate
     * the value and treat it as a removal operation. Alternatively, a custom
     * weigher may be specified on the map to assign an empty value a positive
     * weight.
     *
     * @return A weigher where each element takes one unit of capacity.
     */
    @SuppressWarnings({ "unchecked" })
    public static <E> Weigher<? super Iterable<E>> iterable() {
        return (Weigher<Iterable<E>>) (Weigher<?>) IterableWeigher.INSTANCE;
    }

    /**
     * A weigher where the value is a {@link Collection} and its weight is the
     * number of elements. A map bounded with this weigher will evict when the
     * total number of elements exceeds the capacity rather than the number of
     * key-value pairs in the map.
     * <p>
     * A value with a weight of <tt>0</tt> will be rejected by the map. If a
     * value with this weight can occur then the caller should eagerly evaluate
     * the value and treat it as a removal operation. Alternatively, a custom
     * weigher may be specified on the map to assign an empty value a positive
     * weight.
     *
     * @return A weigher where each element takes one unit of capacity.
     */
    @SuppressWarnings({ "unchecked" })
    public static <E> Weigher<? super Collection<E>> collection() {
        return (Weigher<Collection<E>>) (Weigher<?>) CollectionWeigher.INSTANCE;
    }

    /**
     * A weigher where the value is a {@link List} and its weight is the number
     * of elements. A map bounded with this weigher will evict when the total
     * number of elements exceeds the capacity rather than the number of
     * key-value pairs in the map.
     * <p>
     * A value with a weight of <tt>0</tt> will be rejected by the map. If a
     * value with this weight can occur then the caller should eagerly evaluate
     * the value and treat it as a removal operation. Alternatively, a custom
     * weigher may be specified on the map to assign an empty value a positive
     * weight.
     *
     * @return A weigher where each element takes one unit of capacity.
     */
    @SuppressWarnings({ "unchecked" })
    public static <E> Weigher<? super List<E>> list() {
        return (Weigher<List<E>>) (Weigher<?>) ListWeigher.INSTANCE;
    }

    /**
     * A weigher where the value is a {@link Set} and its weight is the number
     * of elements. A map bounded with this weigher will evict when the total
     * number of elements exceeds the capacity rather than the number of
     * key-value pairs in the map.
     * <p>
     * A value with a weight of <tt>0</tt> will be rejected by the map. If a
     * value with this weight can occur then the caller should eagerly evaluate
     * the value and treat it as a removal operation. Alternatively, a custom
     * weigher may be specified on the map to assign an empty value a positive
     * weight.
     *
     * @return A weigher where each element takes one unit of capacity.
     */
    @SuppressWarnings({ "unchecked" })
    public static <E> Weigher<? super Set<E>> set() {
        return (Weigher<Set<E>>) (Weigher<?>) SetWeigher.INSTANCE;
    }

    /**
     * A weigher where the value is a {@link Map} and its weight is the number
     * of entries. A map bounded with this weigher will evict when the total
     * number of entries across all values exceeds the capacity rather than the
     * number of key-value pairs in the map.
     * <p>
     * A value with a weight of <tt>0</tt> will be rejected by the map. If a
     * value with this weight can occur then the caller should eagerly evaluate
     * the value and treat it as a removal operation. Alternatively, a custom
     * weigher may be specified on the map to assign an empty value a positive
     * weight.
     *
     * @return A weigher where each entry takes one unit of capacity.
     */
    @SuppressWarnings({ "unchecked" })
    public static <A, B> Weigher<? super Map<A, B>> map() {
        return (Weigher<Map<A, B>>) (Weigher<?>) MapWeigher.INSTANCE;
    }

    private enum SingletonWeigher implements Weigher<Object> {
        INSTANCE;

        public int weightOf(Object value) {
            return 1;
        }
    }

    private enum ByteArrayWeigher implements Weigher<byte[]> {
        INSTANCE;

        public int weightOf(byte[] value) {
            return value.length;
        }
    }

    private enum IterableWeigher implements Weigher<Iterable<?>> {
        INSTANCE;

        public int weightOf(Iterable<?> values) {
            if (values instanceof Collection<?>) {
                return ((Collection<?>) values).size();
            }
            int size = 0;
            for (Iterator<?> iterator = values.iterator(); iterator.hasNext();) {
                iterator.next();
                size++;
            }
            return size;
        }
    }

    private enum CollectionWeigher implements Weigher<Collection<?>> {
        INSTANCE;

        public int weightOf(Collection<?> values) {
            return values.size();
        }
    }

    private enum ListWeigher implements Weigher<List<?>> {
        INSTANCE;

        public int weightOf(List<?> values) {
            return values.size();
        }
    }

    private enum SetWeigher implements Weigher<Set<?>> {
        INSTANCE;

        public int weightOf(Set<?> values) {
            return values.size();
        }
    }

    private enum MapWeigher implements Weigher<Map<?, ?>> {
        INSTANCE;

        public int weightOf(Map<?, ?> values) {
            return values.size();
        }
    }
}
