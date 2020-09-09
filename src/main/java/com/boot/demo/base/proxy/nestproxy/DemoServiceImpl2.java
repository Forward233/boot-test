package com.boot.demo.base.proxy.nestproxy;

/**
 * @author: yhl
 * @DateTime: 2020/6/22 21:37
 * @Description:
 */
public class DemoServiceImpl2 implements DemoService {
    @Override
    public void sayHi(String name) {
        System.out.println("2Hi," + name);
    }
}
