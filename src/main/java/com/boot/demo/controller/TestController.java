package com.boot.demo.controller;

import com.boot.demo.config.PersonYmlConfig;
import com.boot.demo.entity.UserBean;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: yhl
 * DateTime: 2019/12/18 20:41
 * Description: write some description
 */
@RestController
public class TestController {

    @RequestMapping("/arg")
    public void testArg(@RequestBody PersonYmlConfig personYmlConfig, UserBean userBean){
        System.out.println(personYmlConfig);
        System.out.println(userBean);
    }

    @RequestMapping("/param_type")
    public String testArg(@RequestParam("type") Integer type, @RequestParam("name") String name) {
        return type + ":" + name;
    }

    @Data
    @ToString
    static class A{
        Integer type;
        String name;
    }

}
