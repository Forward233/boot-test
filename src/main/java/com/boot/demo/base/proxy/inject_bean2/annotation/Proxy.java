package com.boot.demo.base.proxy.inject_bean2.annotation;

import com.boot.demo.base.proxy.inject_bean2.Interceptor;

import java.lang.annotation.*;

/**
 * @author: yhl
 * @DateTime: 2020/6/26 10:20
 * @Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Proxy {
    String value() default "";

    Class<? extends Interceptor> interceptor();
}
