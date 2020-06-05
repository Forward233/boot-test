package com.boot.demo.rpc.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: yhl
 * @DateTime: 2020/6/5 7:42
 * @Description:
 */
@Data
@ToString
public class Student implements Serializable {

    private static final long serialVersionUID = 3769184878896913085L;

    private String name;
    private int age;
    private String sex;

}
