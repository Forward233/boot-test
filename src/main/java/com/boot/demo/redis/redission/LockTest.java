package com.boot.demo.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: yhl
 * @DateTime: 2020/4/30 8:31
 * @Description:
 */
public class LockTest {

    @Autowired
    Redisson redisson;

    public void testLock() {
        RLock lock = redisson.getLock("");
    }
}
