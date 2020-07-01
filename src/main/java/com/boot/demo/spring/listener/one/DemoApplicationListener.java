package com.boot.demo.spring.listener.one;

import org.springframework.context.ApplicationListener;

/**
 * @author: yhl
 * @DateTime: 2020/6/30 20:27
 * @Description:
 */

public class DemoApplicationListener implements ApplicationListener<DemoEvent> {
    @Override
    public void onApplicationEvent(DemoEvent event) {
        event.printMsg();
    }
}
