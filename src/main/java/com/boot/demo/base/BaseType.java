package com.boot.demo.base;

import java.util.Calendar;
import java.util.Date;

/**
 * Author: yhl
 * DateTime: 2019/12/6 7:40
 * Description: write some description
 */
public class BaseType {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        final int year= calendar.get(Calendar.YEAR);
        System.out.println(year);
    }

    static int foo2() {
        final int a = 2; // 声明常量a
        final int b = 3; // 声明常量b
        return a + b;    // 常量表达式
    }

    static int foo3() {
        return 5;
    }

}
