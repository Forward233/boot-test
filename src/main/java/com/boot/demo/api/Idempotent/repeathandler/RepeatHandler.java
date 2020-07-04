package com.boot.demo.api.Idempotent.repeathandler;

import com.github.tomato.support.RepeatToInterceptSupport;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author: yhl
 * @DateTime: 2020/7/4 6:47
 * @Description:
 */
@Slf4j
public class RepeatHandler implements RepeatToInterceptSupport {
    @Override
    public Object proceed(Method method, Object[] args) {
        log.info("-----------------------------my RepeatHandler...");
        return null;
    }
}
