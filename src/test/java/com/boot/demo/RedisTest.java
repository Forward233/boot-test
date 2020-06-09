package com.boot.demo;

import com.boot.demo.redis.three.RedisThree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.StringRedisTemplate;

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
    TaskExecutor taskExecutor;

    @Test
    void testRedis() {
        stringRedisTemplate.opsForValue().set("test_order", "1", 1000, TimeUnit.MILLISECONDS);
        Long test_order = stringRedisTemplate.opsForValue().increment("test_order");
        System.out.println(test_order);
    }

    @Test
    public void testThree() {
        for (int i = 0; i < 20; i++) {
            taskExecutor.execute(() -> redisThree.cacheBreakdown());
        }
    }

}
