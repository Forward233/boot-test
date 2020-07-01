package com.boot.demo.spring.event.event;

/**
 * @author: yhl
 * @DateTime: 2020/6/29 7:23
 * @Description:
 */
public class AbstractContextEvent implements Event{
    private static final long serialVersionUID = -2292289778809116817L;
    private final long timestamp = System.currentTimeMillis();

    public final long getTimestamp() {
        return this.timestamp;
    }
}
