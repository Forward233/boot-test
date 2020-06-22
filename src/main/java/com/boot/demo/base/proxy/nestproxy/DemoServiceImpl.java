package com.boot.demo.base.proxy.nestproxy;

/**
 * @author: yhl
 * @DateTime: 2020/6/22 21:37
 * @Description:
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public void saiHi(String name) {
        System.out.println("Hi," + name);
    }
}
