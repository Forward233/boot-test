package com.boot.demo.spring.event.multicaster;

import com.boot.demo.spring.event.event.Event;
import com.boot.demo.spring.event.listener.ContextListener;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: yhl
 * @DateTime: 2020/6/29 8:14
 * @Description:
 */
public class SimpleApplicationEventMulticaster implements ApplicationEventMulticaster {

    private boolean async;

    private final Set<ContextListener<Event>> contextListeners = new HashSet<>();

    private final Executor executor = new ThreadPoolExecutor(
            5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    public void setAsync(boolean async) {
        this.async = async;
    }

    @Override
    public void addContextListener(ContextListener<Event> contextListener) {
        contextListeners.add(contextListener);
    }

    @Override
    public void removeContextListener(ContextListener<Event> contextListener) {
        contextListeners.remove(contextListener);
    }

    @Override
    public void removeAllContextListeners() {
        contextListeners.clear();
    }

    @Override
    public void multicastEvent(Event event) {
        doMulticastEvent(contextListeners, event);
    }

    private void doMulticastEvent(Set<ContextListener<Event>> contextListeners, Event event) {
        for (ContextListener<Event> contextListener : contextListeners) {
            // 异步广播事件
            if (async) {
                executor.execute(() -> invokeListener(contextListener, event));
                // new Thread(() -> invokeListener(contextListener, event)).start();
                // 同步发布事件，阻塞的方式
            } else {
                invokeListener(contextListener, event);
            }
        }
    }

    private void invokeListener(ContextListener<Event> contextListener, Event event) {
        contextListener.onApplicationEvent(event);
    }
}
