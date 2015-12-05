package com.mark.core;

import com.mark.PlainTestObj1;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: Mark
 * Date  : 15/12/2.
 */
public class DefaultContainerTest {

    private DefaultContainer defaultContainer;


    @Before
    public void setup() {
        defaultContainer = new DefaultContainer("com.mark");
    }

    @Test
    public void testContructor() {
        Assert.assertNotNull(defaultContainer);
    }

    @Test
    public void testName() {
        PlainTestObj1 test1 = defaultContainer.getBeanByName("PlainTestObj1", PlainTestObj1.class);
        Assert.assertNull(test1);
        PlainTestObj1 test2 = defaultContainer.getBeanByName("pto1", PlainTestObj1.class);
        Assert.assertNotNull(test2);
    }

    @Test
    public void testSub() {
        PlainTestObj2 test = defaultContainer.getBeanByName("pto2", PlainTestObj2.class);
        Assert.assertNotNull(test);
    }

    @Test
    public void testInject() {
        PlainTestObj2 test = defaultContainer.getBeanByName("pto2", PlainTestObj2.class);
        Assert.assertNotNull(test.getPto1());
    }

}
