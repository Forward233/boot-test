package com.boot.demo.base.proxy.inject_bean2;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: yhl
 * @DateTime: 2020/6/26 10:21
 * @Description:
 */
@Slf4j
public class PluginProxy implements InvocationHandler {
    private final Object target;
    private final Interceptor interceptor;

    public PluginProxy(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            // 前置业务处理
//            log.info("method[{}] 前置处理", method.getName());
//            interceptor.preHandle();
            log.info("请求参数：{}，对象：{}", args, target);
            Object result = method.invoke(target, args);

            // 后置业务处理
//            log.info("method[{}] 后置处理", method.getName());
//            interceptor.afterHandle();
            log.info("请求结果：{}", result);
            return result;
        } catch (Exception e) {
            return interceptor.catchException(e);
        } finally {
            interceptor.doAfterCompleted();
        }
    }

}

