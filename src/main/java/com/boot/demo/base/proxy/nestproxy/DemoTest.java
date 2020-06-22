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
        demoService.saiHi("yhl");

        System.out.println("----------------");
        DemoServiceProxy proxyNest = new DemoServiceProxy("B");
        // demoService生成的代理类final class $Proxy0 extends Proxy implements DemoService {}
        // DemoService接口的实现，和demoServiceImpl一样。
        DemoService demoServiceNest = (DemoService) proxyNest.getProxy(demoService);
        demoServiceNest.saiHi("hd");
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }
}
