package com.boot.demo;

import com.boot.demo.spring.listener.one.DemoApplicationListener;
import com.boot.demo.spring.listener.one.DemoEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        // addApplicationListener()可由以下方法实现
        // 1.meta-info/spring.factories配置
        // 2.application.properties中配置
        // 3.在事件方法上添加@EventListener
        context.addApplicationListener(new DemoApplicationListener());
        context.publishEvent(new DemoEvent(new Object(), "Hello world"));
    }

    @RequestMapping("test")
    public void test() {
        throw new RuntimeException("hahahaha");
    }
}
