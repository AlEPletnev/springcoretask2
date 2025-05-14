package com.springcore.tasktwo.testbean;

import com.springcore.tasktwo.annotation.Timed;
import org.springframework.stereotype.Component;

@Component
@Timed
public class TestBean {

    private String testStr;

    public void testMethod(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}