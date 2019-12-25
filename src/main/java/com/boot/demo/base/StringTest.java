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

        Object o1 = new Object();
        Object o2 = new Object();
        System.out.println(o1);
        System.out.println(o2);
        o1 = o2;
        System.out.println(o1);
        o2 = null;
        System.out.println(o1);
    }

    void foo(String str) {
        str = "world";
    }
}
