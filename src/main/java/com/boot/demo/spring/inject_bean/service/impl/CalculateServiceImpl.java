package com.boot.demo.spring.inject_bean.service.impl;

import com.alibaba.fastjson.JSON;
import com.boot.demo.spring.inject_bean.annotation.Proxy;
import com.boot.demo.spring.inject_bean.service.CalculateService;
import com.boot.demo.spring.inject_bean.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yhl
 * @DateTime: 2020/6/25 16:21
 * @Description:
 */
@Proxy
@Service
@Slf4j
public class CalculateServiceImpl implements CalculateService {

    @Autowired
    private StatisticsService statisticsService;

    @Override
    public String getResult(String name) {
        log.info(JSON.toJSONString(statisticsService.getList("1", "y")));
        return "26 years old";
    }
}
