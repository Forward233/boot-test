package com.boot.demo.lock;

/**
 * Author: yhl
 * DateTime: 2019/10/12 9:33
 * Description: write some description
 */
public class MultiThread {

    // static 修饰的方法，synchronized就为类锁，相当于synchronized (this)
    public synchronized void printNum(String tag) {
        try {
            int num = 0;
            if (tag.equals("a")) {
                num = 100;
                System.out.println("tag a, set num over!");
                Thread.sleep(1000);
            } else {
                num = 200;
                System.out.println("tag b, set num over!");
            }

            System.out.println("tag " + tag + ", num = " + num);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //2个对象
        final MultiThread m1 = new MultiThread();
        final MultiThread m2 = new MultiThread();

        Thread t1 = new Thread(() -> {
            m1.printNum("a");//使用m1对象
        });

        Thread t2 = new Thread(() -> {
            m2.printNum("b");//使用m2对象
        });

        t1.start();
        t2.start();
    }
}
