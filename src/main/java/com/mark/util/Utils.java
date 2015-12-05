package com.mark.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Mark
 * Date  : 15/12/5.
 */
public class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    public static Class<?> loadClass(String fullClassName) {
        try {
            return Class.forName(fullClassName);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Load Class " + fullClassName + " failed.", e);
            throw new RuntimeException("Load Class " + fullClassName + " failed.", e);
        }
    }

    public static Object newInstance(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("new " + cls.getName() + " instance failed.", e);
            throw new RuntimeException("new " + cls.getName() + " instance failed.", e);
        }
    }


}
