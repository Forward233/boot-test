package com.boot.demo.base.proxy;

import java.lang.reflect.Proxy;

/**
 * @author: yhl
 * @DateTime: 2020/3/9 16:01
 * @Description:
 */
public class Client {
    public static void main(String[] args) {

        final IActor iActor = (IActor) Proxy.newProxyInstance(Client.class.getClassLoader(), new Class[]{IActor.class},
                new ActorHandler(new Actor()));
        iActor.setName("test");
    }


}
