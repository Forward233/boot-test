package com.boot.demo.spring.inject_bean.service;

import java.util.List;

/**
 * @author: yhl
 * @DateTime: 2020/6/25 16:02
 * @Description:
 */
public interface StatisticsService {
    List<String> getList(String code, String name);
}
