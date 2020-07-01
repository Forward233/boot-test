package com.boot.demo.spring.event.multicaster;

import com.boot.demo.spring.event.event.Event;
import com.boot.demo.spring.event.listener.ContextListener;

/**
 * @author: yhl
 * @DateTime: 2020/6/29 7:31
 * @Description:
 */
public interface ApplicationEventMulticaster {

    void addContextListener(ContextListener<Event> contextListener);

    void removeContextListener(ContextListener<Event> contextListener);

    void removeAllContextListeners();

    void multicastEvent(Event event);

}
