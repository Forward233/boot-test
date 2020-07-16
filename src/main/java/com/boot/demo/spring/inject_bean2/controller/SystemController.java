package com.boot.demo.spring.inject_bean2.controller;

import com.boot.demo.spring.inject_bean2.service.ISystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yhl
 * @DateTime: 2020/6/26 10:37
 * @Description:
 */
@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private ISystemService iSystemService;

    @RequestMapping("/saveRecord")
    public void saveRecord() {
        iSystemService.saveRecord();
    }
}
