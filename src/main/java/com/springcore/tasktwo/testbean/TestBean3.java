package com.springcore.tasktwo.testbean;

import com.springcore.tasktwo.annotation.Timed;
import org.springframework.stereotype.Component;

@Component
public class TestBean3 {

    @Timed
    public void testMethod(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Timed
    public void testMethod2(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
