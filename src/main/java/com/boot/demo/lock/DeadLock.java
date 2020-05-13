package com.boot.demo.lock;

/**
 * Author: yhl
 * DateTime: 2019/10/12 13:31
 * Description: write some description
 */
class DeadLock {

    void method1() {
        synchronized (Integer.class) {
            System.out.println("获取到Integer类锁");
            synchronized (String.class) {
                System.out.println("获取到String类锁");
            }
        }
    }

    void method2() {
        synchronized (String.class) {
            System.out.println("获取到Integer类锁");
            synchronized (Integer.class) {
                System.out.println("获取到String类锁");
            }
        }
    }


    void method3() {
        synchronized (this) {
            System.out.println("method3");
        }
    }

    void method4() {
        synchronized (this) {
            System.out.println("method4");
        }
    }

    synchronized void method5(int i) {
        System.out.println(i + " method5");
    }

    synchronized void method6(int i) {
        System.out.println(i + " method6");

    }

    public static void main(String[] args) {
//        final DeadLock deadLock = new DeadLock();
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                deadLock.method1();
//            }
//        }).start();
//
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                deadLock.method2();
//            }
//        }).start();

//        final DeadLock deadLock = new DeadLock();
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                deadLock.method3();
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException ignored) {
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                deadLock.method4();
//            }
//        }).start();

        final DeadLock deadLock1 = new DeadLock();
        final DeadLock deadLock2 = new DeadLock();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                deadLock1.method5(i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                deadLock2.method6(i + 10);
            }
        }).start();

    }
}