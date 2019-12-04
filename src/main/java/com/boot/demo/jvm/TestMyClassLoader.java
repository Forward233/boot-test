package com.boot.demo.jvm;

import java.lang.reflect.InvocationTargetException;

/**
 * Author: yhl
 * DateTime: 2019/12/4 21:16
 * Description: write some description
 */
public class TestMyClassLoader {

    public static void main(String []args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        MyClassLoader mcl = new MyClassLoader();
        Class<?> clazz = Class.forName("com.boot.demo.jvm.People", true, mcl);
        Object obj = clazz.newInstance();

        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());//打印出我们的自定义类加载器
    }
}
