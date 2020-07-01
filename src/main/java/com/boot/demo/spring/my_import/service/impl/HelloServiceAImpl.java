package com.boot.demo.spring.my_import.service.impl;

import com.boot.demo.spring.my_import.service.HelloService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: yhl
 * @DateTime: 2020/7/1 15:03
 * @Description:
 */
@Slf4j
public class HelloServiceAImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        log.info("A say:{},hello", name);
    }
}
