package com.boot.demo.base.proxy.normal;

import java.lang.reflect.Proxy;

/**
 * @author: yhl
 * @DateTime: 2020/3/9 16:01
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        final IActor actor = (IActor) Proxy.newProxyInstance(Client.class.getClassLoader(),
                new Class[]{IActor.class}, new ActorHandler(new PrimaryActor()));
        actor.act(25.0f);
    }


}
