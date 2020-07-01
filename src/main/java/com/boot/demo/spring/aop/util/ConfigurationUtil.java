package com.boot.demo.spring.aop.util;

import com.boot.demo.spring.aop.ProxyBeanHolder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: yhl
 * @DateTime: 2020/7/1 17:44
 * @Description:
 */
public class ConfigurationUtil {

    /**
     * aop标识注解类
     */
    public static final String AOP_POINTCUT_ANNOTATION
            = "com.baomw.annotation.AopJ";
    /**
     * 前置通知注解类
     */
    public static final String BEFORE
            = "com.baomw.annotation.BeforeBaomw";
    /**
     * 后置通知注解类
     */
    public static final String AFTER
            = "com.baomw.annotation.AfterBaomw";
    /**
     * 环绕通知注解类
     */
    public static final String AROUND
            = "com.baomw.annotation.AroundBaomw";
    /**
     * 存放需代理的全部目标对象类
     */
    public static volatile Map<String, List<ProxyBeanHolder>> clazzProxyBeanHolder = new ConcurrentHashMap<>();

}
