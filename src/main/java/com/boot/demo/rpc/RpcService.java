package com.boot.demo.rpc;

import java.lang.annotation.*;

/**
 * @author: yhl
 * @DateTime: 2020/6/5 23:40
 * @Description:
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcService {

    Class<?> value();
}
