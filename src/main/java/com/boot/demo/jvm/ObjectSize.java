package com.boot.demo.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author: yhl
 * @DateTime: 2020/11/27 9:47
 * @Description:  启用指针压缩-XX:+UseCompressedOops(默认开启)，禁止指针压缩:-XX:-UseCompressedOops
 */
public class ObjectSize {

    private String name;
    private long age;
    private Long Age;


    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(new ObjectSize()).toPrintable());
    }
}

