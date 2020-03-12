package com.boot.demo.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: yhl
 * @DateTime: 2020/3/6 7:13
 * @Description:
 * [究竟什么是可重入锁？_Java_Lovnx-CSDN博客](https://blog.csdn.net/rickiyeat/article/details/78314451)
 */
public class ReEntryLock {

    static class ReentrantTest implements Runnable {

        public synchronized void get() {
            System.out.println(Thread.currentThread().getName());
            set();
        }

        public synchronized void set() {
            System.out.println(Thread.currentThread().getName());
        }

        public void run() {
            get();
        }

        public static void main(String[] args) {
            ReentrantTest rt = new ReentrantTest();
            for (; ; ) {
                new Thread(rt).start();
            }
        }
    }

    static class UnreentrantLock {

        private AtomicReference<Thread> owner = new AtomicReference<Thread>();

        public void lock() {
            Thread current = Thread.currentThread();
            //这句是很经典的“自旋”语法，AtomicInteger中也有
            for (; ; ) {
                if (!owner.compareAndSet(null, current)) {
                    return;
                }
            }
        }

        public void unlock() {
            Thread current = Thread.currentThread();
            owner.compareAndSet(current, null);
        }
    }

    static class UnreentrantLockModify {

        private AtomicReference<Thread> owner = new AtomicReference<Thread>();
        private int state = 0;

        public void lock() {
            Thread current = Thread.currentThread();
            if (current == owner.get()) {
                state++;
                return;
            }
            //这句是很经典的“自旋”式语法，AtomicInteger中也有
            for (; ; ) {
                if (!owner.compareAndSet(null, current)) {
                    return;
                }
            }
        }

        public void unlock() {
            Thread current = Thread.currentThread();
            if (current == owner.get()) {
                if (state != 0) {
                    state--;
                } else {
                    owner.compareAndSet(current, null);
                }
            }
        }
    }
}
