package com.boot.demo.spring.aop.annotation;

import java.lang.annotation.*;

/**
 * @author: yhl
 * @DateTime: 2020/7/1 17:51
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableAop {
    String value() default "";
}
