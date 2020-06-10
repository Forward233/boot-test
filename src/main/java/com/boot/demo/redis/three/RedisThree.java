package com.boot.demo.redis.three;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yhl
 * @DateTime: 2020/6/9 20:33
 * @Description:
 */
@Service
@Slf4j
public class RedisThree {

    @Autowired
    private StringRedisTemplate redisTemplate;

    ReentrantLock lock = new ReentrantLock();

    private static AtomicInteger integer = new AtomicInteger();

    public void cacheBreakdown() {
        String key = "hotKey";
        try {
            if (!redisTemplate.hasKey(key)) {
                lock.lock();
                if (redisTemplate.hasKey(key)) {
                    String value = redisTemplate.opsForValue().get(key);
                    log.info("从缓存中获取...");
                    lock.unlock();
                } else {
                    //getFromDB
                    String value = "getFromDB";
                    redisTemplate.opsForValue().set(key, value, 1000, TimeUnit.SECONDS);
                    lock.unlock();
                    log.info("拿到锁，设置缓存，释放锁....");
                    return;
                }
            } else {
                log.info("-----------从缓存中获取...");
            }

        }finally {
        }

    }
}
