package com.boot.demo.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: yhl
 * @DateTime: 2021/7/12 6:19
 * @Description: 滑动窗口计数器
 */
@Slf4j
@Component
public class SlidingWindowCounter {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private Redisson redisson;

    /**
     * redis List 也可以实现
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
        RLock lock = redisson.getLock("");
        // 如果在try{}代码块中加锁失败，finally中的代码无论如何都会执行，但是由于当前线程加锁失败并没有持有lock对象锁 ，所以程序会抛出异常。
        lock.lock();
        try {
            if (getWindowCount(key) < maxcount) {
                increment(key, windowInSecond);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return false;
    }


    /**
     * https://blog.csdn.net/m0_38001814/article/details/113643374
     * @param key
     * @param maxcount
     * @param windowInSecond
     * @return
     */
    public Boolean isAccessWithLua(String key, Integer maxcount, Integer windowInSecond) {

        return false;
    }

}
