package com.boot.demo.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: yhl
 * @DateTime: 2020/5/12 7:02
 * @Description:
 */
public class ActorHandler implements InvocationHandler {

    private final Object target;

    public ActorHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start...");
        final Object invoke = method.invoke(target, args);
        System.out.println("end...");
        return invoke;
    }
}
