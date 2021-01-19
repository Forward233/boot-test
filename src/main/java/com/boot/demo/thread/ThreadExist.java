package com.boot.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yhl
 * @DateTime: 2021/1/13 17:36
 * @Description:
 */
public class ThreadExist {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println("parent thread begin ");

        ChildThread t1 = new ChildThread("thread1");
        ChildThread t2 = new ChildThread("thread2");
        t1.start();
        t2.start();

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 30; i++) {
            //ExecutorService executorService = Executors.newFixedThreadPool(20);
            executorService.execute(() -> {
                int i1 = atomicInteger.incrementAndGet();
                System.out.println(i1);
            });
        }
        System.out.println("parent thread over ");
    }

}
