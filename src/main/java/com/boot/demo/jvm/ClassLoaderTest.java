package com.boot.demo.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: yhl
 * @DateTime: 2019/12/4 15:26
 * @Description:
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception{

        ClassLoader myLoader = new ClassLoader() {
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
                }catch (IOException e)
                {
                    throw new ClassNotFoundException();
                }
            }
        };

        Object obj = myLoader.loadClass("com.boot.demo.jvm.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.boot.demo.jvm.ClassLoaderTest);
    }
}
