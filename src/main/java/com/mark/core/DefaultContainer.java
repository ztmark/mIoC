package com.mark.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: Mark
 * Date  : 15/11/30.
 */
public class DefaultContainer implements Container {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultContainer.class);
    private ConcurrentHashMap<String, Object> container = new ConcurrentHashMap<>();


    public DefaultContainer(String... basePackage) {
        for (String p : basePackage) {
            scanPackage(p);
        }
    }

    private void scanPackage(String basePackage) {
        // TODO
    }

    @Override
    public <T> T getBeanByName(String name, Class<T> type) {
        //noinspection unchecked
        return (T) container.get(name);
    }
}
