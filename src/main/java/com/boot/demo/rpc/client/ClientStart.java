package com.boot.demo.rpc.client;

import com.boot.demo.rpc.entity.Student;
import com.boot.demo.rpc.server.service.StudentService;

/**
 * @author: yhl
 * @DateTime: 2020/6/6 17:23
 * @Description:
 */
public class ClientStart {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1",8088);
        StudentService service =  proxy.getProxy(StudentService.class);
//        System.out.println(service.getInfo());
        Student student = new Student();
        student.setName("yhl");
        student.setAge(26);
        student.setSex("male");
        service.printInfo(student);
    }
}
