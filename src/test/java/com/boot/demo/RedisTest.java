package com.boot.demo;

import com.boot.demo.redis.three.RedisThree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Author: yhl
 * DateTime: 2019/12/17 23:57
 * Description: write some description
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisThree redisThree;

    @Autowired
    private TaskExecutor taskExecutor;

    @Test
    void testRedis() throws InterruptedException {
        taskExecutor.execute(()->{
            try {
                stringRedisTemplate.opsForValue().set("test_order", "1", 1000, TimeUnit.MILLISECONDS);
                Long test_order = stringRedisTemplate.opsForValue().increment("test_order");
                System.out.println(test_order);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread.sleep(300000);

    }

    @Test
    public void testThree() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 20; i++) {
            taskExecutor.execute(() -> {
                try {
                    redisThree.cacheBreakdown(countDownLatch);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(3000);
        countDownLatch.countDown();
        Thread.sleep(300000);
    }

    public static void main(String[] args) {
        String rootPath = RedisTest.class.getResource("/").getPath();
        System.out.println(rootPath);
        int asInt = Arrays.stream(new int[]{1, 2, 3}).max().getAsInt();
        System.out.println(asInt);
    }
}
