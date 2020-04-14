package com.boot.demo.thread;

import java.util.PriorityQueue;

/**
 * @author: yhl
 * @DateTime: 2020/4/11 12:04
 * @Description:
 */
public class CusProTest {

    private static final PriorityQueue<Integer> queue = new PriorityQueue<Integer>(10);

    public static void main(String[] args) {
        // 生产者
        new Thread(() ->{
            while (true) {
                // 模拟生产耗时
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (queue){
                    if (queue.size() == 10) {
                        try {
                            System.out.println("队列已满，wait，等待消费......");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(1);
                    queue.notifyAll();
                    System.out.println("向队列取中插入一个元素，队列空间：" + queue.size());
                }
            }
        }).start();

        // 消费者
        new Thread(() ->{
            while (true) {
                synchronized (queue){
                    if (queue.isEmpty()) {
                        try {
                            System.out.println("队列为空，wait，等待生产......");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    queue.notifyAll();
                    System.out.println("从队列取中取走一个元素，队列空间：" + queue.size());
                }

                // 模拟消费耗时
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

