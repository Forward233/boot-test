package com.boot.demo.base.duotai;

/**
 * @author: yhl
 * @DateTime: 2020/4/8 8:03
 * @Description:
 */
class Cat extends Animal {
    int num = 80;
    static int age = 90;
    String name = "tomCat";

    public void eat() {
        System.out.println("猫吃饭");
    }

    public void sleep() {
        System.out.println("猫在睡觉");
    }

    public void catchMouse() {
        System.out.println("猫在抓老鼠");
    }

    @Override
    protected void hookTest() {
        System.out.println("hook test");
    }

    public void call() {
        System.out.println("miao miao miao....");
    }
}
