package com.boot.demo.api.Idempotent.controller;

import com.boot.demo.api.Idempotent.request.UserRequest;
import com.github.tomato.annotation.Repeat;
import com.github.tomato.annotation.TomatoToken;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: yhl
 * @DateTime: 2020/7/4 6:18
 * @Description:
 */
@RestController
@RequestMapping("api")
public class ApiIdempotentController {

    @Repeat(scope = 6000000)
    @GetMapping()
    public String getUser(@TomatoToken String name) {
        System.out.println(System.currentTimeMillis() + ":" + name);
        String s = System.currentTimeMillis() + ":" + name;
        return s;
    }


    @Repeat
    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postUserName(@TomatoToken("userName") @RequestBody UserRequest userRequest) {
        System.out.println(System.currentTimeMillis() + ":" + userRequest.getUserName());
        String s = System.currentTimeMillis() + ":" + userRequest.getUserName();
        return s;
    }

    @Repeat(throwable = NullPointerException.class, message = "禁止重复提交")
    @PostMapping(value = "/form")
    public String postUserName(@TomatoToken("userName") HttpServletRequest userRequest) {
        System.out.println(System.currentTimeMillis() + ":" + userRequest.getParameter("userName"));
        String s = System.currentTimeMillis() + ":" + userRequest.getParameter("userName");
        return s;
    }
}
