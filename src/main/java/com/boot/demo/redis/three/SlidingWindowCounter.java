package com.boot.demo.redis.three;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: yhl
 * @DateTime: 2021/7/12 6:19
 * @Description:
 */
@Slf4j
@Component
public class SlidingWindowCounter {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * lua脚本实现最佳
     * @param key
     * @param windowInSec
     */
    public void increment(String key, Integer windowInSec) {
        long currentTime = System.currentTimeMillis();
        long maxScore = currentTime - windowInSec * 1000;
        try {
            redisTemplate.multi();
            BoundZSetOperations<String, String> bound = redisTemplate.boundZSetOps(key);
            bound.removeRangeByScore(0, maxScore);
            bound.add(currentTime + "_" + Math.random() * 10, currentTime);
            bound.expire(windowInSec, TimeUnit.SECONDS);
            redisTemplate.exec();
        } catch (Exception e) {
            redisTemplate.discard();
            log.error("窗口内容错误：{}", e.getMessage());
        }
    }

    public Long getWindowCount(String key) {
        try {
            return redisTemplate.opsForZSet().zCard(key);
        } catch (Exception e) {
            log.error("获取窗口大小错误:{}", e.getMessage());
            return 0L;
        }
    }

    public Boolean isAccess(String key, Integer maxcount, Integer windowInSecond) {
        // 加redis锁

        return false;
    }

}
