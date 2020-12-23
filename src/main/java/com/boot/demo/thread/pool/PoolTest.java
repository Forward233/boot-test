package com.boot.demo.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: yhl
 * @DateTime: 2020/10/23 6:40
 * @Description:
 */
public class PoolTest {
    public static void main(String[] args) {
        ExecutorService newWorkStealingPool = Executors.newWorkStealingPool();
        for (int i = 0; i < 20; i++) {
            newWorkStealingPool.submit(() ->{
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
