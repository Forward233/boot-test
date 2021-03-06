package com.boot.demo.spring.inject_bean.factory;

import com.boot.demo.spring.inject_bean.proxy.ServiceProxy;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author: yhl
 * @DateTime: 2020/6/25 15:35
 * @Description:
 */
@Data
@Slf4j
public class ServiceFactory<T> implements FactoryBean<T>{

    private Class<T> interfaceType;
    private Object target;
    private Class<T> interfaces;

    public ServiceFactory(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
    }

    @Override
    public T getObject() {
        //这里主要是创建接口对应的实例，便于注入到spring容器中
        log.info("FactoryBean实例化自定义bean...:{}", target);
        InvocationHandler handler = new ServiceProxy(target);
        return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(),
                new Class[]{interfaces}, handler);
    }

    @Override
    public Class<T> getObjectType() {
        return interfaces;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
