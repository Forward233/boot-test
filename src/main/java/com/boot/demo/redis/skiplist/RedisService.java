package com.boot.demo.redis.skiplist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author: yhl
 * @DateTime: 2020/4/6 10:06
 * @Description:
 */
@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void zSet() {
    }
}
