package com.boot.demo.spring.inject_bean2.interceptor;

/**
 * @author: yhl
 * @DateTime: 2020/6/26 10:21
 * @Description:
 */
public interface Interceptor {
    /**
     * 执行被代理方法前执行
     */
    void preHandle();

    /**
     * 执行被代理方法后执行
     */
    void afterHandle();

    /**
     * 被代理方法执行异常时执行
     *
     * @param exception ex
     * @return rlt
     */
    Object catchException(Exception exception);

    /**
     * 代理方法执行结束时执行
     */
    void doAfterCompleted();
}
