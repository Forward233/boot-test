package com.boot.demo.spring.my_import.config;

import com.boot.demo.spring.my_import.service.HelloService;
import com.boot.demo.spring.my_import.service.impl.HelloServiceAImpl;
import com.boot.demo.spring.my_import.service.impl.HelloServiceBImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: yhl
 * @DateTime: 2020/7/1 15:10
 * @Description: [Spring（32）——ImportSelector介绍_Elim的博客-CSDN博客_importselector]
 * (https://blog.csdn.net/elim168/article/details/88131614)
 */
@Configuration
//@Import(HelloImportSelector.class)
@Import({HelloServiceAImpl.class, HelloServiceBImpl.class})
public class HelloConfiguration {

    @Bean
    public HelloService helloServiceA() {
        return new HelloServiceAImpl();
    }

    @Bean
    public HelloService helloServiceB() {
        return new HelloServiceBImpl();
    }
}
