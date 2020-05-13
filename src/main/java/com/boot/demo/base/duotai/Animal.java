package com.boot.demo.base.duotai;

/**
 * @author: yhl
 * @DateTime: 2020/4/8 8:03
 * @Description:
 */
class Animal {
    int num = 10;
    static int age = 20;

    public void eat() {
        System.out.println("动物吃饭");
    }

    public static void sleep() {
        System.out.println("动物在睡觉");
    }

    public void run() {
        System.out.println("动物在奔跑");
    }
}