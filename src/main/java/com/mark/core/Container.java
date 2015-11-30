package com.mark.core;

/**
 * Author: Mark
 * Date  : 15/11/30.
 */
public interface Container {


    public <T> T getBeanByName(String name, Class<T> type);

}
