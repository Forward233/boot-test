package com.boot.demo.lock;

/**
 * @author: yhl
 * @DateTime: 2020/10/21 7:19
 * @Description:
 */
public class Uninterruptible {
    private static final Object o1 = new Object();
    private static final Object o2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        t2.start();
        Thread.sleep(2000);
        System.out.println("before interrupt");
        t1.interrupt();
        t2.interrupt();
        System.out.println("end interrupt");
        t1.join();
        t2.join();
    }

    static Thread t1 = new Thread(() -> {
        synchronized (o1){
            try {
                System.out.println("start t1");
                Thread.sleep(1000);
                synchronized (o2){
                    System.out.println("t1 lock o2");
                }
            } catch (InterruptedException e) {
                System.out.println("t1 interrupted");
                e.printStackTrace();
            }
        }
    });

    static Thread t2 = new Thread(() -> {
        synchronized (o2){
            try {
                System.out.println("start t2");
                Thread.sleep(1000);
                synchronized (o1){
                    System.out.println("t2 lock o1");
                }
            } catch (InterruptedException e) {
                System.out.println("t2 intterrupted");
                e.printStackTrace();
            }
        }
    });
}
