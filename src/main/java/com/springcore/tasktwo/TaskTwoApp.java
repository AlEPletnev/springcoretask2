package com.springcore.tasktwo;

import com.springcore.tasktwo.config.AppConf;
import com.springcore.tasktwo.metricStorage.MethodMetricStorage;
import com.springcore.tasktwo.testbean.TestBean;
import com.springcore.tasktwo.testbean.TestBean3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TaskTwoApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConf.class);
        TestBean testBean = context.getBean(TestBean.class);
        testBean.testMethod();
        TestBean3 testBean3 = context.getBean(TestBean3.class);
        testBean3.testMethod();
        MethodMetricStorage methodMetricStorage = context.getBean(MethodMetricStorage.class);
        System.out.println(methodMetricStorage.getList().toString());
    }
}
