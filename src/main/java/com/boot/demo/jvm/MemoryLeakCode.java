package com.boot.demo.jvm;

/**
 * @author: yhl
 * @DateTime: 2020/5/1 10:18
 * @Description:
 */
public class MemoryLeakCode {

    public static void main(String[] args) {

        Object object1 = new Object();
        Object object2 = new Object();
        object1 = object2;

    }
}
