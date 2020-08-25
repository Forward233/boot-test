package com.boot.demo.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: yhl
 * DateTime: 2019/10/11 9:59
 * Description: write some description
 */
public class YieldTest {
    public static void main(String[] args) {
        //
        //测试yield
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("当前线程为: " + Thread.currentThread().getName() + i);
                if (i == 5) {
                    Thread.yield();
                }
            }
        };
//        Thread thread = new Thread(runnable, "A");
//        Thread thread1 = new Thread(runnable, "B");
//        thread.start();
//        thread1.start();

        Map<String, Integer> map = new HashMap<>();
        map.put("1", 10);
        Integer put = map.put("2", 20);
        System.out.println(put);
    }
}
