package com.boot.demo.api.Idempotent.config;

import com.boot.demo.api.Idempotent.repeathandler.RepeatHandler;
import com.github.tomato.support.RepeatToInterceptSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author: yhl
 * @DateTime: 2020/7/4 6:44
 * @Description:
 */
@Configuration
@Order(-1)
public class IdempotentBeanConfig {

    @Bean
    public RepeatToInterceptSupport repeatToInterceptSupport(){
        return new RepeatHandler();
    }
}
