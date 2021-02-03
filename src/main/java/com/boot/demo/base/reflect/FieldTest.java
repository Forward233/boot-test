package com.boot.demo.base.reflect;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: yhl
 * @DateTime: 2020/7/17 7:05
 * @Description:
 */
public class FieldTest {

    public Map
            <Integer, String> map = new HashMap<>();

    public static void main(String[] args) throws NoSuchFieldException {
        String s = "K";
        switch (s) {
            case "A":
                System.out.println("aaaaaaaa");
                /*
                 * 注意：此处如果没有添加break，则代码会继续执行，即使不满足case条件也会执行，直到遇到break
                 */
                //break;
            case "B":
                System.out.println("bbbbbbbb");
            case "C":
                System.out.println("bbbbbbbb");
                break;
            default:
                System.out.println("others");
                break;
        }
    }


}
