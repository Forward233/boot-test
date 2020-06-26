package com.boot.demo.base.proxy.inject_bean.controller;

import com.boot.demo.base.proxy.inject_bean.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yhl
 * @DateTime: 2020/6/26 10:37
 * @Description:
 */
@RestController
@RequestMapping("/cal")
public class CalculateController {

    @Autowired
    private CalculateService calculateService;

    @RequestMapping("/info")
    public String getCalculate() {
        return calculateService.getResult("yhl");
    }
}
