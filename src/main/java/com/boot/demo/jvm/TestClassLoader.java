package com.boot.demo.jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Author: yhl
 * DateTime: 2019/12/4 21:12
 * Description: 自定义类加载
 *              需要继承ClassLoader，重写findClass
 */
public class TestClassLoader extends ClassLoader {

    /**
     *
     *
     *  此路径即时想要加载的外部class路径
     */
    private String filePath;

    TestClassLoader(String filePath){
        this.filePath = filePath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        File file = new File(filePath);
        try {
            byte[] bytes = getClassBytes(file);
            // 如果读取的.class文件为空，则抛出ClassNotFoundException异常
            if (bytes == null) {
                throw new ClassNotFoundException();
            }
            //defineClass方法可以把二进制流字节组成的文件转换为一个java.lang.Class
            Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    private byte[] getClassBytes(File file) throws Exception {
        // 这里要读入.class文件的字节，因此要使用字节流
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer by = ByteBuffer.allocate(1024);
        while (true) {
            int i = fc.read(by);
            if (i == 0 || i == -1)
                break;
            by.flip();
            wbc.write(by);
            by.clear();
        }
        fis.close();
        return baos.toByteArray();
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 创建自定义加载器实例
        TestClassLoader mcl = new TestClassLoader("D:/Test.class");

        // classPackage为自定义的class中的包路径
        String classPackage = "com.jvm.test.Test";
        // 通过自定义类加载器加载此class,
        // Class<?> obj = Class.forName(classPackage, true, mcl).newInstance();
        Object obj = mcl.loadClass(classPackage).newInstance();
        // 打印加载的类对象
        System.out.println(obj);
        // 打印加载此类的类加载器
        System.out.println(obj.getClass().getClassLoader());//打印出我们的自定义类加载器
    }
}
