package com.boot.demo.base;

/**
 * Author: yhl
 * DateTime: 2019/12/6 7:40
 * Description: write some description
 */
public class BaseType {

    public static void main(String[] args) {
        String a = "a";
        String b = new StringBuffer("a").append("b").toString();
        String c = new String("a");
        String d = "ab";
        System.out.println(b == d);
    }

}
