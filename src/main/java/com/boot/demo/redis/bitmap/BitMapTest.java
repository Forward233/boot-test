package com.boot.demo.redis.bitmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author: yhl
 * @DateTime: 2020/5/17 18:49
 * @Description:
 */
public class BitMapTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    void bitMaptest(){
        long userId = 313213L;
        redisTemplate.opsForValue().setBit("test_bit", userId, true);
    }

    public static void main(String[] args) {
        String b = "a";
        System.out.println(b.getBytes().length);
    }

}
