package com.boot.demo.api.limit_rate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: yhl
 * @DateTime: 2021/1/8 7:02
 * @Description:
 */
public class CountLimit {

    private final static AtomicLong ZERO = new AtomicLong(0);
    private AtomicLong counter = ZERO;
    private static long timestamp = System.currentTimeMillis();
    private long permitsPerSecond;

    public CountLimit(long permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
    }

    public boolean tryAcquire() {
        long now = System.currentTimeMillis();
        if (now - timestamp < 1) {
            if (counter.get() < permitsPerSecond) {
                counter.incrementAndGet();
                return true;
            } else {
                return false;
            }
        } else {
            synchronized (CountLimit.class){
                if (counter.get() < permitsPerSecond) {
                    counter.incrementAndGet();
                    return true;
                } else {
                    System.out.println(System.currentTimeMillis());
                    counter = ZERO;
                    timestamp = now;
                    return false;
                }
            }
        }
    }

    public static void main(String[] args) {
        CountLimit countLimit = new CountLimit(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 500; i++) {
            executorService.submit(() -> {
                boolean result = countLimit.tryAcquire();
            });
        }
    }
}