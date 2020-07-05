package com.boot.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: yhl
 * @DateTime: 2020/7/6 7:24
 * @Description:
 */
@Slf4j
public class ScheduledTest {

    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);


    public static void main(String[] args) {
        log.info("begin time:{}", new Date());
        // scheduleWithFixedDelay 第一次执行在initialDelay之后，只有每次执行时间间隔为 任务处理时间+delay
        // scheduleAtFixedRate 第一次执行在initialDelay之后，如果任务执行时间小于period，
        // 则执行间隔为period，大于，执行间隔为任务处理时间。
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.info("exec time:{}", new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 3, 2, TimeUnit.SECONDS);
    }
}
