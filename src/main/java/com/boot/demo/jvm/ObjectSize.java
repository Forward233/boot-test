package com.boot.demo.jvm;

import org.openjdk.jol.info.ClassLayout;

import java.util.List;

/**
 * @author: yhl
 * @DateTime: 2020/11/27 7:58
 * @Description:
 */
public class ObjectSize {

    private int i;
    private long l;
    private Object obj;
    private List list;

    public static void main(String[] args) {
        ClassLayout classLayout = ClassLayout.parseInstance(new ObjectSize());
        System.out.println(classLayout.toPrintable());
    }

}
