package com.boot.demo.spring.inject_bean2.factory;

import com.boot.demo.spring.inject_bean2.interceptor.Interceptor;
import com.boot.demo.spring.inject_bean2.proxy.PluginProxy;
import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @author: yhl
 * @DateTime: 2020/6/26 10:21
 * @Description:
 */
@Data
public class ProxyFactoryBean<T> implements FactoryBean<T> {
    private Class<T> interfaces;
    private Interceptor interceptor;
    private Object target;

    @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(),
                new Class[]{interfaces}, new PluginProxy(target, interceptor));
    }

    @Override
    public Class<?> getObjectType() {
        return interfaces;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}