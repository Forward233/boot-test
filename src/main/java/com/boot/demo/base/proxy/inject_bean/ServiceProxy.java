package com.boot.demo.base.proxy.inject_bean;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: yhl
 * @DateTime: 2020/6/25 15:59
 * @Description:
 */
@Slf4j
public class ServiceProxy implements InvocationHandler {

    private Object target;

    public ServiceProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("调用前，参数：{}，对象：{}", args, target);
        //这里可以得到参数数组和方法等，可以通过反射，注解等，进行结果集的处理
        //mybatis就是在这里获取参数和相关注解，然后根据返回值类型，进行结果集的转换
        Object result = method.invoke(target, args);
        log.info("调用后，结果：{}", result);
        return result;
    }

}
