package com.mark.core;

import com.mark.PlainTestObj1;
import com.mark.annotation.Bean;
import com.mark.annotation.Inject;

/**
 * Author: Mark
 * Date  : 15/12/5.
 */
@Bean(name = "pto2")
public class PlainTestObj2 {

    @Inject
    private PlainTestObj1 pto1;

    public void setPto1(PlainTestObj1 p1) {
        this.pto1 = p1;
    }

    public PlainTestObj1 getPto1() {
        return pto1;
    }
}
