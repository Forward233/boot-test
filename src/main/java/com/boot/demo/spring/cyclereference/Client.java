package com.boot.demo.spring.cyclereference;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: yhl
 * @DateTime: 2020/8/25 11:20
 * @Description:
 */
public class Client {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(args);
        Boss boss = applicationContext.getBean("boss", Boss.class);
        System.out.println(boss);
    }
}
