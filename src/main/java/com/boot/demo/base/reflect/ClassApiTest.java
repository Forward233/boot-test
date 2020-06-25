package com.boot.demo.base.reflect;

/**
 * @author: yhl
 * @DateTime: 2020/6/25 16:33
 * @Description:
 */
public class ClassApiTest {

    private String className;
    public static void main(String[] args) {
        Class<?>[] declaredClasses = ClassApiTest.class.getDeclaredClasses();
        for (Class<?> declaredClass : declaredClasses) {
            System.out.println(declaredClass.getSimpleName());
        }
    }
}
