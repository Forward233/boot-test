package com.boot.demo;

import com.boot.demo.config.PersonYmlConfig;
import com.boot.demo.entity.UserBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
