package com.boot.demo.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author: yhl
 * @DateTime: 2020/11/5 11:06
 * @Description:
 */
public class CountdownLatchSample {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            //lambda中只能只用final的变量
            final int times = i;
            new Thread(() -> {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在赶路");
                    Thread.sleep(1000 * times);
                    System.out.println("子线程" + Thread.currentThread().getName() + "到公司了");
                    //调用latch的countDown方法使计数器-1
                    latch.countDown();
                    System.out.println("子线程" + Thread.currentThread().getName() + "开始工作");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    latch.countDown();
                }
            }).start();
        }


        try {
            System.out.println("门卫等待员工上班中...");
            //主线程阻塞等待计数器归零
            latch.await();
            System.out.println("员工都来了,门卫去休息了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
