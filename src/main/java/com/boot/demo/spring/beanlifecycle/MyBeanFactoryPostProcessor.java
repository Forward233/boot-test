package com.boot.demo.spring.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author: yhl
 * @DateTime: 2020/12/8 6:38
 * @Description:
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("execute BeanLifeCycle BeanFactoryPostProcessor.postProcessBeanFactory()");
    }
}
