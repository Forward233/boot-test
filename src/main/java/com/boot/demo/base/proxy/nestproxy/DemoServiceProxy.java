package com.boot.demo.base.proxy.nestproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: yhl
 * @DateTime: 2020/6/22 21:37
 * @Description:
 */
public class DemoServiceProxy implements InvocationHandler {

    private String name;
    private Object target;

    public DemoServiceProxy(String name) {
        this.name = name;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(name + "执行前...");
        // 反射
        Object object = method.invoke(target, args);
        System.out.println(name + "执行后...");
        return object;
    }

    public Object getProxy(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
