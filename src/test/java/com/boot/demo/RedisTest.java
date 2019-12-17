package com.boot.demo;

import com.alibaba.fastjson.JSON;
import com.boot.demo.config.PersonYmlConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;

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
        Set<String> range = stringRedisTemplate.opsForZSet().range("test-json", 0, -1);
        for (String s : range) {
            System.out.println(s);
            System.out.println(JSON.toJavaObject(JSON.parseObject(s), PersonYmlConfig.class));
        }
    }
}
