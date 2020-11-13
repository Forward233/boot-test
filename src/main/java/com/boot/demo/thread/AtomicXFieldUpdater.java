package com.boot.demo.thread;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author: yhl
 * @DateTime: 2020/9/19 10:04
 * @Description: 1.将volatile变量改造成有CAS需求的变量
 * 2.需要大量使用Atomic类的时候，为了节约内存，用fieldupdater去替换，替换一个AtomicInteger可以节约至少16字节的内存。
 */
public class AtomicXFieldUpdater {

    @Data
    static class Account {
        private volatile int money;
        //private AtomicInteger moneyAtomic = new AtomicInteger(0);




        // AtomicIntegerFieldUpdater中更保持线程安全的字段必须是 public volatile修饰的
        private static final AtomicIntegerFieldUpdater<Account> UPDATER = AtomicIntegerFieldUpdater.newUpdater(Account.class, "money");

        public Account(int money) {
            this.money = money;
        }

        public void increaseMoney() {
            UPDATER.incrementAndGet(this);
        }
    }

    public static void main(String[] args) {
        List<Long> digital = new ArrayList<>();
        for (int i = 0; i < 80000000; i++) {
            digital.add((long) i);
        }

        long l = System.currentTimeMillis();
        long sum = digital.stream().mapToLong(a -> a).sum();
        System.out.println(sum + "：" + (System.currentTimeMillis() - l));

        long l2 = System.currentTimeMillis();
        long sum2 = digital.parallelStream().mapToLong(a -> a).sum();
        System.out.println(sum2 + "：" + (System.currentTimeMillis() - l2));
    }
}


