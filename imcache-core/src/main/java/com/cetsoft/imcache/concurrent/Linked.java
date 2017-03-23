/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 3, 2015
 */
package com.KyleDing.imcache.concurrent;

/**
 * An element that is linked on the Deque.
 */
public interface Linked<T extends Linked<T>> {

    /**
     * Retrieves the previous element or <tt>null</tt> if either the element is
     * unlinked or the first element on the deque.
     */
    T getPrevious();

    /** Sets the previous element or <tt>null</tt> if there is no link. */
    void setPrevious(T prev);

    /**
     * Retrieves the next element or <tt>null</tt> if either the element is
     * unlinked or the last element on the deque.
     */
    T getNext();

    /** Sets the next element or <tt>null</tt> if there is no link. */
    void setNext(T next);
}
