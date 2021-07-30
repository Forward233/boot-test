package com.boot.demo.lambda.optional;

import lombok.Data;

import java.util.Optional;

/**
 * @author: yhl
 * @DateTime: 2020/8/25 5:32
 * @Description:
 * [Java高级（三）：Optional的巧用 - 知乎](https://zhuanlan.zhihu.com/p/40966718)
 */
public class OptionalTest {


    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        User user = new User();
        user.setId(1L);
        user.setMobile("18729041176");
        userInfo.setUser(user);

        UserDetail userDetail = new UserDetail();
        userDetail.setAge(26);
        userDetail.setGender(1);
        userInfo.setUserDetail(userDetail);
        Optional.of(userInfo).ifPresent(a -> a.getUser().setMobile("111"));
        System.out.println(user);
    }


    @Data
    static
    class UserInfo{
        private User user;
        private UserDetail userDetail;
    }

    @Data
    static
    class User{
        private Long id;
        private String mobile;
    }

    @Data
    static
    class UserDetail{
        private Integer age;
        private Integer gender;
    }
}
