package com.boot.demo.spring.event.listener;

import com.boot.demo.spring.event.event.ContextStartEvent;
import com.boot.demo.spring.event.event.Event;

/**
 * @author: yhl
 * @DateTime: 2020/6/29 18:05
 * @Description:
 */
public class
ContextStartEventListener implements ContextListener<Event> {

    @Override
    public void onApplicationEvent(Event event) {
        if (event instanceof ContextStartEvent) {
            System.out.println("容器启动----------------->启动时间为：" + ((ContextStartEvent) event).getTimestamp());
        }
    }
}
