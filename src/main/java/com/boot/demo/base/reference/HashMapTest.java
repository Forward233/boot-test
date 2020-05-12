package com.boot.demo.base.reference;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.HashMap;

/**
 * @author: yhl
 * @DateTime: 2020/5/1 19:30
 * @Description:
 */
@Data
public class HashMapTest {

    private int a;

    public static void main(String[] args) {

        HashMap<String, HashMapTest> hashMap = Maps.newHashMap();
        HashMapTest hashMapTest = new HashMapTest();
        hashMapTest.setA(1);
        String key = "1";
        hashMap.put(key, hashMapTest);
        System.out.println(hashMap);
        hashMapTest.setA(2);
        key = null;
        System.out.println(hashMap);
    }
}
