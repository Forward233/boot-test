package com.boot.demo.base.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: yhl
 * @DateTime: 2020/6/24 22:42
 * @Description:
 */
public class CgLibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Before Method Invoke");
        Object o = proxy.invokeSuper(obj, args);
        System.out.println("After Method Invoke");
        return o;
    }
}
