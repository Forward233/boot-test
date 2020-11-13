package com.boot.demo.base.polymorphism;

import java.sql.Timestamp;

/**
 * @author: yhl
 * @DateTime: 2020/6/15 21:07
 * @Description:
 */
public class Test {

    // q:无法再调用父类被覆盖的方法
    // a:可以在子类写一个方法，用super关键字，调用父类的方法
    public static void main(String[] args) {
        Parent parent = new Children();
        System.out.println(parent.name);
        parent.age();
        parent.hair();
        Children children = (Children) parent;
        children.inspire();
        try {
            String time = "1970-01-01 00:00:00.000000000.000000000";
            com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
            jsonObject.put("time", time);

            Timestamp timestamp = jsonObject.getTimestamp("time");
            System.out.println("time:" + timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
