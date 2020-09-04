package com.boot.demo.base.extend;

/**
 * @author: yhl
 * @DateTime: 2020/8/31 21:40
 * @Description:
 */
public class Son extends Parent {

    public void doSon() {
        System.out.println("do son");
        super.doParent();
    }
}
