package com.boot.demo.spring.inject_bean.annotation;

import java.lang.annotation.*;

/**
 * @author: yhl
 * @DateTime: 2020/6/25 21:43
 * @Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Proxy {
    String value() default "";
}
