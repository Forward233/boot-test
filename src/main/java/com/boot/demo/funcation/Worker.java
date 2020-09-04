package com.boot.demo.funcation;

import java.util.function.Function;

/**
 * Author: yhl
 * DateTime: 2019/10/11 10:12
 * Description: write some description
 */
public class Worker {

    // 参数是R，返回值是T的参数
    private String startWork(String job) {
        return "Job " + job + " start.";
    }

    private String inWork(String job) {
        return "Job " + job + " ing.";
    }

    private String finishWork(String job) {
        return "Job " + job + " finish.";
    }

    // 接收参数是R，返回值是T的参数
    private void handleWork(Function<String, String> funcHandler) {
        String handle = funcHandler.apply("");
        System.out.println(handle);
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.handleWork(new Worker()::startWork);
        worker.handleWork(s -> worker.inWork("yhl"));
    }

}
