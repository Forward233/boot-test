package com.boot.demo.funcation;

/**
 * Author: yhl
 * DateTime: 2019/10/11 10:12
 * Description: write some description
 */
public class Worker {

    // 参数是R，返回值是T的参数
    private String doWork(String job) {
        return "Job " + job + " done.";
    }

    // 接收参数是R，返回值是T的参数
    private void handleWork(Handler<String, String> theFuncHandler) {
        System.out.println(theFuncHandler.handle("job-" + System.currentTimeMillis()));
    }

    public static void main(String[] args) {
        Worker worker = new Worker();

        worker.handleWork(worker::doWork);
    }

    private interface Handler<T, R> {
        T handle(R r);
    }
}
