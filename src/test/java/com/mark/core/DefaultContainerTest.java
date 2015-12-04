package com.mark.core;

import org.junit.Test;

/**
 * Author: Mark
 * Date  : 15/12/2.
 */
public class DefaultContainerTest {

    @Test
    public void testContructor() {
        DefaultContainer defaultContainer = new DefaultContainer("com.mark");
        com.mark.Test obj = defaultContainer.getBeanByName("Test", com.mark.Test.class);
        obj.sayHi();
    }


}
