package com.boot.demo.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: yhl
 * @DateTime: 2019/12/4 15:26
 * @Description:
 */
public class ClassLoaderTest extends ClassLoader {
    public static void main(String[] args) throws Exception {

        /**
         * 重写loadClass,违背了双亲委派原则。
         * 因为双亲委派的逻辑是在loadClass中通过递归实现
         * 正确操作为重写findClass
         */
        ClassLoader load = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream resourceAsStream = getClass().getResourceAsStream(fileName);

                    if (resourceAsStream == null)
                        return super.loadClass(name);

                    byte[] b = new byte[resourceAsStream.available()];
                    resourceAsStream.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException();
                }
            }
        };


        ClassLoader find = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream resourceAsStream = getClass().getResourceAsStream(fileName);
                if (resourceAsStream == null) {
                    return super.findClass(name);
                } else {
                    byte[] b = new byte[0];
                    try {
                        b = new byte[resourceAsStream.available()];
                        resourceAsStream.read(b);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return defineClass(name, b, 0, b.length);
                }
            }
        };

        // 重写loadClass，破坏双亲委派原则，直接加载类到jvm，则会使用自定义的类加载器
        Object loadObject = load.loadClass("java.lang.Integer").newInstance();
        System.out.println("load:" + loadObject.getClass().getClassLoader());
        System.out.println(loadObject instanceof com.boot.demo.jvm.ClassLoaderTest);
        System.out.println("------------------------------------------------------");
        // 重写findClass，由于此类在类路径classpath下已经编译好，册会用applicationClassLoader来加载，
        // 如果要使用自定义累加器的加载，则应该使用类路径classpath外的一个class文件，并且类路径下不能包含此class
        // 才能使用到自定义的类加载器
        Object findObject = find.loadClass("com.boot.demo.jvm.ClassLoaderTest").newInstance();
        System.out.println("find:" + findObject.getClass().getClassLoader());
        System.out.println(findObject instanceof com.boot.demo.jvm.ClassLoaderTest);
    }
}
