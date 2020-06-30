package com.boot.demo.spring.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: yhl
 * @DateTime: 2020/6/30 7:31
 * @Description:
 */
@Slf4j
@Component
public class ApplicationStartEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("My ApplicationStartEventListener event...");
    }
}
