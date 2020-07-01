package com.boot.demo.spring.inject_bean;

import com.boot.demo.spring.inject_bean.service.CalculateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: yhl
 * @DateTime: 2020/6/25 16:05
 * @Description:
 */
@SpringBootTest
class CalculateServiceTest {

    @Autowired
    private CalculateService calculateService;

    @Test
    public void getResult() {
        String yhl = calculateService.getResult("yhl");
        System.out.println(yhl);
    }
}