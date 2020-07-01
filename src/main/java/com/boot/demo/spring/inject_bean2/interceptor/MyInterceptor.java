package com.boot.demo.spring.inject_bean2.interceptor;

import com.boot.demo.spring.inject_bean2.interceptor.Interceptor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: yhl
 * @DateTime: 2020/6/26 10:21
 * @Description:
 */
@Slf4j
public class MyInterceptor implements Interceptor {
    @Override
    public void preHandle() {
        log.info("preHandle");
    }

    @Override
    public void afterHandle() {
        log.info("afterHandle");
    }

    @Override
    public Object catchException(Exception exception) {
        log.info("catchException异常啦。。。");
        return null;
    }

    @Override
    public void doAfterCompleted() {
        log.info("doAfterCompleted");
    }
}

