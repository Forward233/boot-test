package com.boot.demo.base.polymorphism;

/**
 * @author: yhl
 * @DateTime: 2020/6/15 21:05
 * @Description:
 */
public class Children extends Parent{

    public String name = "c";

    public void age() {
        System.out.println("25");
    }

    public void inspire() {
        System.out.println("has more inspire");
    }
}
