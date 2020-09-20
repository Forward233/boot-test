package com.boot.demo.mybatis.config;

import com.boot.demo.mybatis.plugin.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yhl
 * @DateTime: 2020/6/23 22:58
 * @Description:
 */
@Configuration
public class Config {

    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

//    @Bean
//    public PerformanceMonitorInterceptor performanceInterceptor() {
//        PerformanceMonitorInterceptor performanceInterceptor = new PerformanceMonitorInterceptor();
//        return performanceInterceptor;
//    }
}
