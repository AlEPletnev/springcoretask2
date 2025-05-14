package com.springcore.tasktwo.beanpostprocessor;

import com.springcore.tasktwo.annotation.Timed;
import com.springcore.tasktwo.metricStorage.MethodMetricStorage;
import com.springcore.tasktwo.metricStorage.MetricMethod;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
public class TimedBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private MethodMetricStorage methodMetricStorage; // Тут бы что придумать как не инжектить бин в пост процессор

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Timed timedOnClass = bean.getClass().getAnnotation(Timed.class);
        if(timedOnClass != null){
            return createCGLIBProxyForReloadAllMethod(bean); // CGLIB не сгенерит прокси если нет конструктора по умолчанию, тоже эту часть надо как-то обходить
        }
        Method[] methods = bean.getClass().getDeclaredMethods();
        for(Method method : methods){
            Timed timedOnMethod = method.getAnnotation(Timed.class);
            if(timedOnMethod != null){
                String nameReloadMethod = method.getName();
                return createCGLIBProxyForReloadOneMethod(bean,nameReloadMethod);
            }
        }
        return bean;
    }

    private Object createCGLIBProxyForReloadAllMethod(Object bean){
        Class<?> beanClass = bean.getClass();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanClass);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                long startTime = System.currentTimeMillis();
                Object object = method.invoke(bean,args);
                long endTime = System.currentTimeMillis();
                long executionTime = endTime - startTime;
                String nameClass = beanClass.toString();
                String nameMethod = method.getName();
                LocalDateTime localDateTime = LocalDateTime.now();
                MetricMethod metricMethod = new MetricMethod(nameClass,nameMethod,localDateTime,executionTime);
                methodMetricStorage.push(metricMethod);
                return object;
            }
        });
        return enhancer.create();
    }

    private Object createCGLIBProxyForReloadOneMethod(Object bean, String nameMethod){
        Class<?> beanClass = bean.getClass();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanClass);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                if(nameMethod.equals(method.getName())){
                    long startTime = System.currentTimeMillis();
                    Object object = method.invoke(bean,args);
                    long endTime = System.currentTimeMillis();
                    long executionTime = endTime - startTime;
                    String nameClass = beanClass.toString();
                    String nameMethod = method.getName();
                    LocalDateTime localDateTime = LocalDateTime.now();
                    MetricMethod metricMethod = new MetricMethod(nameClass,nameMethod,localDateTime,executionTime);
                    methodMetricStorage.push(metricMethod);
                    return object;
                }
                return method.invoke(bean,args);
            }
        });
        return enhancer.create();
    }
}