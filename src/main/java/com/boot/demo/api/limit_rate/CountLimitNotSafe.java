package com.boot.demo.api.limit_rate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: yhl
 * @DateTime: 2021/1/8 7:02
 * @Description:
 */
public class CountLimitNotSafe {

    /**
     * 计数器归零
     */
    private final AtomicLong ZERO = new AtomicLong(0);
    /**
     * 计数器
     */
    private AtomicLong counter = ZERO;
    /**
     * 起始时间
     */
    private static long timestamp = System.currentTimeMillis();
    /**
     * 允许的请求书
     */
    private final long permitsPerSecond;

    public CountLimitNotSafe(long permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
    }

    public boolean tryAcquire() {
        long now = System.currentTimeMillis();
        // 如果当前时间已经过了1ms
        if (now - timestamp < 1L) {
            // 判断此时间间隔计数器是否大于设置的请求数
            if (counter.get() < permitsPerSecond) {
                counter.incrementAndGet();
                return true;
            } else {
                return false;
            }
        } else {
            // 重新设置起始时间，计数器归零
            // 在这打印一下进入这个else的时间，用于观测，如果打印时间相等，则证明多个线程进入了
            // 此处如果有多个线程进入，会重复设置时间起始时间和计数器，线程不安全
            System.out.println("重置计数起始时间：" + now);
            counter = ZERO;
            timestamp = now;
            return false;
        }
    }

    public static void main(String[] args) {
        // 设置10个允许请求数
        CountLimitNotSafe countLimit = new CountLimitNotSafe(10);
        // 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 模拟100个线程
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                boolean result = countLimit.tryAcquire();
            });
        }
    }
}