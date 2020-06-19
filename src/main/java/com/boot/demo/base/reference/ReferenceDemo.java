package com.boot.demo.base.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.ToString;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author: yhl
 * @DateTime: 2020/6/18 7:15
 * @Description:
 */
public class ReferenceDemo {

    public static void main(String[] args) {
        softReferenceTest();
    }

    @SneakyThrows
    public static void softReferenceTest()  {
        Product product = new Product("test");
        ReferenceQueue<Product> referenceQueue = new ReferenceQueue<>();
        SoftReference<Product> softReference = new SoftReference<>(new Product("yhl"), referenceQueue);

        System.gc();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(product);
        System.out.println(softReference.get());

        Reference<? extends Product> reference;
        while ((reference = referenceQueue.poll()) != null) {
            System.out.println(reference.get());
        }
    }

    @SneakyThrows
    public static void weakReferenceTest()  {
        Product product = new Product("test");
        ReferenceQueue<Product> referenceQueue = new ReferenceQueue<>();
        WeakReference<Product> weakReference = new WeakReference<>(new Product("yhl"), referenceQueue);

        System.gc();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(product);
        System.out.println(weakReference.get());

        Reference<? extends Product> reference;
        while ((reference = referenceQueue.poll()) != null) {
            System.out.println(reference.get());
        }
    }

    @SneakyThrows
    public static void weakHashMapTest()  {
        Map<Product, Integer> map = new WeakHashMap<>();
        Product product = new Product("test");
        map.put(product, 1);
        System.out.println(map);
        product.setName("yhl");
        System.out.println(map);
        System.gc();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(product);
        System.out.println(map);
    }


    @SneakyThrows
    public static void hashMapTest()  {
        HashMap<Product, Integer> map = new HashMap<>();
        Product product = new Product("test");
        map.put(product, 1);
        System.out.println(map);
        product.setName("yhl");
        System.out.println(map);
        // 将指向product实例的置为null,但是
        product = null;
        System.gc();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(product);
        System.out.println(map);
    }

    @Data
    @AllArgsConstructor
    @ToString
    static class Product{
        private String name;
    }
}
