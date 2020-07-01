package com.boot.demo.spring.event;

import com.boot.demo.spring.event.event.ContextDestroyEvent;
import com.boot.demo.spring.event.event.ContextRunningEvent;
import com.boot.demo.spring.event.event.ContextStartEvent;
import com.boot.demo.spring.event.listener.ContextRunningEventListener;
import com.boot.demo.spring.event.listener.ContextStartEventListener;
import com.boot.demo.spring.event.multicaster.SimpleApplicationEventMulticaster;
import com.boot.demo.spring.event.listener.ContextDestroyEventListener;
import com.boot.demo.spring.event.multicaster.ApplicationEventMulticaster;

/**
 * @author: yhl
 * @DateTime: 2020/6/29 7:42
 * @Description:
 * [Spring是如何实现事件监听机制的？ Spring源码（二） - 掘金](https://juejin.im/post/5e421bfc6fb9a07cd80f1354)
 */
public class Client {

    public static void main(String[] args) {
        // 新建SimpleApplicationEventMulticaster对象，并添加容器生命周期监听器
        ApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.addContextListener(new ContextStartEventListener());
        eventMulticaster.addContextListener(new ContextRunningEventListener());
        eventMulticaster.addContextListener(new ContextDestroyEventListener());
        // 发射容器启动事件ContextStartEvent
        eventMulticaster.multicastEvent(new ContextStartEvent());
        // 发射容器正在运行事件ContextRunningEvent
        eventMulticaster.multicastEvent(new ContextRunningEvent());
        // 发射容器销毁事件ContextDestroyEvent
        eventMulticaster.multicastEvent(new ContextDestroyEvent());


        // 时间监听器只监听对应的事件
    }
}
