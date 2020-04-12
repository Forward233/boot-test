package com.boot.demo.base.statics;

/**
 * @author: yhl
 * @DateTime: 2020/4/11 13:21
 * @Description:
 */
public class StaticTest {
    static int x, y;

    static {
        x = 5;
    }



    public static void main(String[] args) {
        x--;
        m();
        System.out.println(x + y++ + x);
    }

    public static void m(){
        y = x++ + ++x;
    }

}
