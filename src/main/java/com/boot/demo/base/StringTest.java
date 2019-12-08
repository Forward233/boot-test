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

        int[] a = {1,2};
        int[] b = a;
        b[1] = 5;
        System.out.println(a[1]);

    }

    void foo(String str) {
        str = "world";
    }
}
