package com.boot.demo.spring.inject_bean.service.impl;

import com.boot.demo.spring.inject_bean.annotation.Proxy;
import com.boot.demo.spring.inject_bean.service.StatisticsService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: yhl
 * @DateTime: 2020/6/25 21:40
 * @Description:
 */
@Proxy
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Override
    public List<String> getList(String code, String name) {
        return Lists.newArrayList("1", "2", "3");
    }
}
