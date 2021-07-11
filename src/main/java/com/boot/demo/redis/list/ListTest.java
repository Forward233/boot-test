package com.boot.demo.redis.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author: yhl
 * @DateTime: 2021/6/5 15:02
 * @Description:
 */
@Service
public class ListTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public static void main(String[] args) {
        for (;;) {
        }
    }
}
