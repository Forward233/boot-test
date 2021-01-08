package com.boot.demo.base;

/**
 * Author: yhl
 * DateTime: 2019/12/6 7:40
 * Description: write some description
 */
public class BaseType {
    public int data = 0;
    public void increment() {
        data++;
        System.out.println(data);
    }

    public static void main(String[] args) {
        BaseType baseType = new BaseType();
        baseType.increment();
        System.out.println(baseType.data);
    }
}
