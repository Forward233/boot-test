package com.boot.demo.base.proxy.cglib;

import com.boot.demo.base.proxy.nestproxy.DemoServiceImpl;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author: yhl
 * @DateTime: 2020/6/24 22:46
 * @Description:
 */
public class CgLibTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        CgLibProxy proxy = new CgLibProxy();
        enhancer.setSuperclass(DemoServiceImpl.class);
        enhancer.setCallback(proxy);
        DemoServiceImpl demoService = (DemoServiceImpl) enhancer.create();
        demoService.sayHi("yhl");
    }
}
