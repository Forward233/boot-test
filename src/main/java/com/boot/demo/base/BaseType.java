package com.boot.demo.base;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Author: yhl
 * DateTime: 2019/12/6 7:40
 * Description: write some description
 */
public class BaseType {

    public static void main(String[] args) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        final int year= calendar.get(Calendar.YEAR);
        System.out.println(year);
    }

}
