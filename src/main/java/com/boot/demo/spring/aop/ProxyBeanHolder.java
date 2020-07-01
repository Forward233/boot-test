package com.boot.demo.spring.aop;

import lombok.Data;

/**
 * @author: yhl
 * @DateTime: 2020/7/1 14:22
 * @Description:
 * [Spring Aop底层原理详解（利用spring后置处理器实现AOP）_baomw的博客-CSDN博客_spring aop 原理](https://blog.csdn.net/baomw/article/details/84262006)
 */
@Data
public class ProxyBeanHolder {

    //通知类名称
    private volatile String className;
    //通知方法名称
    private volatile String methodName;
    //注解类名称
    private volatile String annotationName;
}
