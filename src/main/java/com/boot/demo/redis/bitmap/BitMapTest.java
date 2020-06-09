package com.boot.demo.redis.bitmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: yhl
 * @DateTime: 2020/5/17 18:49
 * @Description:
 */
public class BitMapTest {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    void test() {
//        redisTemplate.opsForValue().setBit()
    }

    public static void main(String[] args) {

    }

}
