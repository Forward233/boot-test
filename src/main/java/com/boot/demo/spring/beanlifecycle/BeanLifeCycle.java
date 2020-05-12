package com.boot.demo.spring.beanlifecycle;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author: yhl
 * @DateTime: 2020/5/12 13:11
 * @Description:
 */
@Data
public class BeanLifeCycle implements InitializingBean, DisposableBean, BeanFactoryAware, BeanNameAware, ApplicationContextAware {

    private String name;

    private ApplicationContext applicationContext;

    public BeanLifeCycle() {
        System.out.println("BeanTestLifeCycle no parameters constructor");
    }

    public BeanLifeCycle(String name) {
        this.name = name;
        System.out.println("BeanTestLifeCycle parameters constructor");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("execution BeanFactoryAware.setBeanFactory()");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("execution BeanNameAware.setBeanName()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("execution InitializingBean.afterPropertiesSet()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("execution DisposableBean.destroy()");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("execution @PostConstruct()");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("execution @PreDestroy()");
    }

    public void initMethod() {
        System.out.println("execution @Bean.initMethod()");
    }

    public void destroyMethod() {
        System.out.println("execution @Bean.destroyMethod()");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("execution ApplicationContextAware.setApplicationContext()");
        this.applicationContext = applicationContext;
    }
}
