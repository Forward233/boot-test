package com.boot.demo.spring.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: yhl
 * @DateTime: 2020/5/12 13:38
 * @Description:
 * [Spring Bean的生命周期（非常详细） - Chandler Qian - 博客园](https://www.cnblogs.com/zrtqsk/p/3735273.html)
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanLifeCycle) {
            System.out.println("execute BeanLifeCycle BeanPostProcessor.postProcessBeforeInitialization()");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanLifeCycle) {
            System.out.println("execute BeanLifeCycle BeanPostProcessor.postProcessAfterInitialization()");
        }
        return bean;
    }
}
