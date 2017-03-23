/*
 *
 *
 * Author : Kyle Ding
 * Date   : Nov 8, 2013
 */
package com.KyleDing.imcache.cache.search.filter;

import java.lang.reflect.Field;

import com.KyleDing.imcache.cache.search.AttributeException;

/**
 * The Class ArithmeticFilter.
 */
public abstract class ArithmeticFilter extends LogicalFilter {

    /** The attribute name. */
    private String attributeName;

    /** The expected value. */
    protected Object value;

    /**
     * Instantiates a new equals to filter.
     *
     * @param attributeName the attribute name
     * @param value the expected value
     */
    public ArithmeticFilter(String attributeName, Object value) {
        this.attributeName = attributeName;
        this.value = value;
    }

    /**
     * Gets the attribute name.
     *
     * @return the attribute name
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * Gets the indexed key.
     *
     * @param object the object
     * @return the indexed key
     */
    protected Object getAttributeValue(Object object) {
        try {
            Field field = object.getClass().getDeclaredField(attributeName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            throw new AttributeException(e);
        }
    }

}
