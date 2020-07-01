package com.boot.demo.spring.my_import.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: yhl
 * @DateTime: 2020/7/1 15:13
 * @Description:
 */
@SpringBootTest
class HelloServiceTest {

//    @Autowired
//    private HelloService helloService;

    @Autowired
    private List<HelloService> helloServiceList;

    @Test
    void sayHello() {
        helloServiceList.forEach(a -> a.sayHello("yhl"));
//        helloServiceList.sayHello("yhl");
    }
}