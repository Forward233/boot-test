package com.boot.demo.base.statics;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        x--;
        m();
        System.out.println(x + y++ + x);
        JSONObject json = new JSONObject();
        json.put("a", "1");
        json.put("b", "2");

//        final A a = StaticTest.GetInstance(A.class);
//        System.out.println(a);
        final Constructor<B> constructor = B.class.getConstructor(new Class[]{String.class});
        A a = new A("a");
        final B b1 = B.class.newInstance();
        System.out.println(b1.getA());


        final B b = constructor.newInstance("a");
        System.out.println(b.getA());

        new B();
    }

    public static void m(){
        y = x++ + ++x;
    }

    static <T> T GetInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    @Data
    static class A{
        public A() {
        }
        public A(String a) {
            this.a = a;
        }
        private String a;
        private String b;
    }

    static class B extends A {
        public B() {
        }
        public B(String a) {
            super(a);
        }
    }
}
