package com.boot.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Author: yhl
 * DateTime: 2019/12/17 23:57
 * Description: write some description
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testJson(){
        stringRedisTemplate.opsForValue().set("test_order", "1");
        Long test_order = stringRedisTemplate.opsForValue().decrement("test_order");
        System.out.println(test_order);
    }
}
