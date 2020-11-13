package com.boot.demo.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: yhl
 * @DateTime: 2020/11/5 11:07
 * @Description:
 */
public class CyclicBarrierSample {
    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("所有人都准备好了裁判开始了");
        });
        for (int i = 0; i < 10; i++) {
            //lambda中只能只用final的变量
            final int times = i;
            new Thread(() -> {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在准备");
                    Thread.sleep(1000 * times);
                    System.out.println("子线程" + Thread.currentThread().getName() + "准备好了");
                    cyclicBarrier.await();
                    System.out.println("子线程" + Thread.currentThread().getName() + "开始跑了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }
}
