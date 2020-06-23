package com.boot.demo.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: yhl
 * @DateTime: 2020/6/23 13:31
 * @Description:
 */
@Data
@TableName("t_person")
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String dateOfBirth;
    private String placeOfBirth;
}
