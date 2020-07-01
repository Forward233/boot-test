package com.boot.demo.spring.event.listener;

import com.boot.demo.spring.event.event.ContextRunningEvent;
import com.boot.demo.spring.event.event.Event;

import java.util.concurrent.TimeUnit;

/**
 * @author: yhl
 * @DateTime: 2020/6/29 18:07
 * @Description:
 */
public class ContextRunningEventListener implements ContextListener<Event> {

    @Override
    public void onApplicationEvent(Event event){
        if (event instanceof ContextRunningEvent) {
            System.out.println("容器开始运行。。。");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("容器运行结束。。。");
        }
    }
}
