package com.boot.demo.base.proxy.nestproxy;

/**
 * @author: yhl
 * @DateTime: 2020/6/22 21:42
 * @Description:
 */
public class DemoTest {

    public static void main(String[] args) {
        DemoService demoServiceImpl = new DemoServiceImpl();
        DemoServiceProxy proxy = new DemoServiceProxy("A");
        DemoService demoService = (DemoService) proxy.getProxy(demoServiceImpl);
        demoService.sayHi("yhl");

        System.out.println("----------------");
        DemoServiceProxy proxyNest = new DemoServiceProxy("B");
        // demoService生成的代理类final class $Proxy0 extends Proxy implements DemoService {}
        // DemoService接口的实现，和demoServiceImpl一样。
        DemoService demoService2 = (DemoService) proxyNest.getProxy(demoService);
        demoService2.sayHi("hd");
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 执行顺序
        // 1.demoService2(实际为$Proxy1，继承Proxy0).sayHi()，即调用$Proxy1.sayHi(),执行DemoServiceProxy中的invoke()方法。
        // 2.执行method.invoke()时，调用Proxy0的invoke()方法。执行执行DemoServiceProxy中的invoke
    }
}
