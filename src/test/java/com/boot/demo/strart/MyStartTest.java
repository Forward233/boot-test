package com.boot.demo.strart;

import com.boot.demo.DemoApplicationTests;
import com.starter.mystarter.service.HelloService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: yhl
 * @DateTime: 2020/5/13 16:04
 * @Description:
 */
public class MyStartTest extends DemoApplicationTests {

    @Autowired
    private HelloService helloService;

    @Test
    public void testStarter() {
        final String world = helloService.sayHello("world");
        System.out.println(world);
    }

}

