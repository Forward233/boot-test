package com.boot.demo.mybatis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: yhl
 * @DateTime: 2020/6/23 22:45
 * @Description:
 */
@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonInfoService personInfoService;

    @Test
    void updatePerson() {
        personInfoService.updatePerson();
    }
}