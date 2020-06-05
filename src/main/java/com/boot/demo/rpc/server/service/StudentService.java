package com.boot.demo.rpc.server.service;

import com.boot.demo.rpc.entity.Student;

/**
 * @author: yhl
 * @DateTime: 2020/6/5 7:42
 * @Description:
 */
public interface StudentService {

    Student getInfo();

    Boolean printInfo(Student student);
}
