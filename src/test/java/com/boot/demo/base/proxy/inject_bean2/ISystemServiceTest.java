package com.boot.demo.base.proxy.inject_bean2;

import com.boot.demo.base.proxy.inject_bean2.service.ISystemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: yhl
 * @DateTime: 2020/6/26 10:25
 * @Description:
 */
@SpringBootTest
class ISystemServiceTest {

    @Autowired
    private ISystemService systemService;

    @Test
    void saveRecord() {
        systemService.saveRecord();
    }
}