package com.boot.demo.rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: yhl
 * @DateTime: 2020/6/5 20:35
 * @Description:
 */
public class RpcClientProxy implements InvocationHandler {
    private String host;
    private int port;

    public RpcClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}

