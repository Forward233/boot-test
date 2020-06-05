package com.boot.demo.rpc.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.boot.demo.rpc.entity.Student;
import com.boot.demo.rpc.server.service.StudentService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: yhl
 * @DateTime: 2020/6/5 8:28
 * @Description:
 */
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Override
    public Student getInfo() {
        Student student = new Student();
        student.setName("yhl");
        student.setAge(18);
        student.setSex("male");
        return student;
    }

    @Override
    public Boolean printInfo(Student student) {
        log.info("---------Student-----------:{}",
                JSON.toJSONString(student, SerializerFeature.PrettyFormat));
        return true;
    }
}
