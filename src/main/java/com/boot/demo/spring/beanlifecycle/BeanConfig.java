package com.boot.demo.spring.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yhl
 * @DateTime: 2020/5/12 13:35
 * @Description:
 */
@Configuration
public class BeanConfig {

    ReentrantLock lock = new ReentrantLock();
    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")

    public BeanLifeCycle getBeanTestLifeCycle() {
        return new BeanLifeCycle();
    }
}
