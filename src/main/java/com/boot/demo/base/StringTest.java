package com.boot.demo.base;

/**
 * Author: yhl
 * DateTime: 2019/12/6 7:51
 * Description: write some description
 */
public class StringTest {

    public static void main(String[] args) {
        String str = "hello";
        StringTest test = new StringTest();
        test.foo(str);
        System.out.println(str);
        StringBuilder sb = new StringBuilder("hello");
        test.foo(sb);
        System.out.println(sb.toString());
    }

    void foo(String str) {
        str = "world";
    }

    void foo(StringBuilder sb){
        sb.append(" world");
    }
}
