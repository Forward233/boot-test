package com.boot.demo.api.limit_rate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: yhl
 * @DateTime: 2021/1/8 7:02
 * @Description:
 */
public class CountLimitSafe {

    private final AtomicLong ZERO = new AtomicLong(0);
    private AtomicLong counter = ZERO;
    private static long timestamp = System.currentTimeMillis();
    private final long permitsPerSecond;

    public CountLimitSafe(long permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
    }

    public boolean tryAcquire() {
        long now = System.currentTimeMillis();
        if (now - timestamp < 1) {
            return getAcquire();
        } else {
            // 加锁
            synchronized (CountLimitSafe.class){
                // 第一个拿到的锁会重置计数器
                // 后面进入else的线程，再次进行获取许可
                if (now - timestamp < 1) {
                    return getAcquire();
                } else {
                    System.out.println("重置计数起始时间：" + now);
                    counter = ZERO;
                    timestamp = now;
                    return false;
                }
            }
        }
    }

    private boolean getAcquire() {
        if (counter.get() < permitsPerSecond) {
            counter.incrementAndGet();
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        CountLimitSafe countLimitSafe = new CountLimitSafe(10);
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 500; i++) {
            executorService.submit(() -> {
                boolean result = countLimitSafe.tryAcquire();
            });
        }
    }
}