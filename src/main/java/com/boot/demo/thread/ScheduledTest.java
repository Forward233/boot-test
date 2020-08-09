package com.boot.demo.thread;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author: yhl
 * @DateTime: 2020/7/6 7:24
 * @Description:
 */
@Slf4j
public class ScheduledTest {

    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("begin time:{}", new Date().getTime());
        // scheduleWithFixedDelay 第一次执行在initialDelay之后，只有每次执行时间间隔为 任务处理时间+delay
        // scheduleAtFixedRate 第一次执行在initialDelay之后，如果任务执行时间小于period，
        // 则执行间隔为period，大于，执行间隔为任务处理时间。
//        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(() -> {
//            log.info("exec time:{}", new Date());
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, 3, 2, TimeUnit.SECONDS);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Callable<Integer> runnable = () -> {
            int i = new Random().nextInt(100);
            return i;
        };
        List<Future<Integer>> futures = Lists.newArrayList();
        Future<Integer> submit;
        for (int i = 0; i < 5; i++) {
            submit = executorService.submit(runnable);
            futures.add(submit);
        }

        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }
    }
}
