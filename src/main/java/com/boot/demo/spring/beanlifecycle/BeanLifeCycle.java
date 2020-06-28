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
        System.out.println("execute BeanFactoryAware.setBeanFactory()");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("execute BeanNameAware.setBeanName()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("execute InitializingBean.afterPropertiesSet()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("execute DisposableBean.destroy()");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("execute @PostConstruct()");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("execute @PreDestroy()");
    }

    public void initMethod() {
        System.out.println("execute @Bean.initMethod()");
    }

    public void destroyMethod() {
        System.out.println("execute @Bean.destroyMethod()");
    }

    //ApplicationContextAware和EmbeddedValueResolverAware等等在后置处理器中调用
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("execute ApplicationContextAware.setApplicationContext()");
    }
}
