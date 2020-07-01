package com.boot.demo.spring.my_import.service.impl;

import com.boot.demo.spring.my_import.service.HelloService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: yhl
 * @DateTime: 2020/7/1 15:04
 * @Description:
 */
@Slf4j
public class HelloServiceBImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        log.info("B say:{},hello", name);
    }
}
