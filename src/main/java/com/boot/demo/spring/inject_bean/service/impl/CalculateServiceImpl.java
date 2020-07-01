package com.boot.demo.spring.inject_bean.service.impl;

import com.boot.demo.spring.inject_bean.annotation.Proxy;
import com.boot.demo.spring.inject_bean.service.CalculateService;
import org.springframework.stereotype.Service;

/**
 * @author: yhl
 * @DateTime: 2020/6/25 16:21
 * @Description:
 */
@Proxy
@Service
public class CalculateServiceImpl implements CalculateService {

    @Override
    public String getResult(String name) {
        return "26 years old";
    }
}
