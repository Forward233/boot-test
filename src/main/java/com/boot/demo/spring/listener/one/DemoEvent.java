package com.boot.demo.spring.listener.one;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

/**
 * @author: yhl
 * @DateTime: 2020/6/30 20:24
 * @Description:
 */
@Slf4j
public class DemoEvent extends ApplicationEvent {

    private String message;
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public DemoEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public void printMsg() {
        log.info("监听到自定义时间消息：{}", message);
    }

}
