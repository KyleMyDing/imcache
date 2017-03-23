/*
 * Copyright (C) 2015 KyleDing, http://www.kyleding.com
 *
 *
 * Author : Kyle Ding
 * Date   : May 20, 2014
 */
package com.KyleDing.imcache.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * The Class SpringCacheExample.
 */
@Component
public class SpringCacheExample {

    /** The cache manager. */
    @Autowired
    CacheManager cacheManager;

    public static void example() {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("exampleContext.xml");
        SpringCacheExample example = context.getBean(SpringCacheExample.class);
        example.getBook(0);
        example.getBook(0);
    }

    /**
     * Gets the book.
     *
     * @param id the id
     * @return the book
     */
    @Cacheable("books")
    public Book getBook(int id) {
        return new Book();
    }

    /**
     * The Class Book.
     */
    public class Book {

        /** The id. */
        private int id;

        /** The name. */
        private String name;

        /**
         * Gets the id.
         *
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * Sets the id.
         *
         * @param id the new id
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * Gets the name.
         *
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the name.
         *
         * @param name the new name
         */
        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        example();
    }
}
