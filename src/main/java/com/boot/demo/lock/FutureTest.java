package com.boot.demo.lock;

import java.util.concurrent.*;

/**
 * @author: yhl
 * @DateTime: 2019/12/20 15:17
 * @Description:
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final ExecutorService executorService = Executors.newFixedThreadPool(3);
        final Future<String> submit = executorService.submit(() -> "future test!");
        final String s = submit.get();
        System.out.println(s);

        final Callable<String> callable = () -> "callable test";
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}