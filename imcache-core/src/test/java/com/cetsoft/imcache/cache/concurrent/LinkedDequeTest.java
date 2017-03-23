/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : Aug 4, 2015
 */
package com.KyleDing.imcache.cache.concurrent;

import java.util.ArrayList;

import org.junit.Test;

import com.KyleDing.imcache.concurrent.Linked;
import com.KyleDing.imcache.concurrent.LinkedDeque;

public class LinkedDequeTest {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void linkedDeque() {
        LinkedDeque deque = new LinkedDeque();
        deque.add(new Node<Integer>());
        deque.addAll(new ArrayList<Object>());
        deque.addFirst(new Node<Integer>());
        deque.addLast(new Node<Integer>());
        deque.clear();
        deque.contains(2);
        deque.descendingIterator();
        deque.iterator();
        deque.moveToBack(new Node<Integer>());
        deque.moveToFront(new Node<Integer>());
        deque.offer(new Node<Integer>());
        deque.offerFirst(new Node<Integer>());
        deque.offerLast(new Node<Integer>());
        deque.peek();
        deque.peekFirst();
        deque.peekLast();
        deque.poll();
        deque.pollFirst();
        deque.peekLast();
        deque.push(new Node<Integer>());
        deque.remove();
        deque.remove(2323);
        deque.removeFirst();
        deque.removeFirstOccurrence(232);
        deque.removeLast();
        deque.removeLastOccurrence(32);
        deque.size();
    }

    private static class Node<T> implements Linked<Node<T>> {

        Node<T> next, prev;

        @Override
        public Node<T> getPrevious() {
            return prev;
        }

        @Override
        public void setPrevious(Node<T> prev) {
            this.prev = prev;
        }

        @Override
        public Node<T> getNext() {
            return next;
        }

        @Override
        public void setNext(Node<T> next) {
            this.next = next;
        }

    }
}
