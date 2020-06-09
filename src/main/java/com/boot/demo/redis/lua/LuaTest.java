package com.boot.demo.redis.lua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: yhl
 * @DateTime: 2020/6/9 7:39
 * @Description:
 */
public class LuaTest {

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    public void testScript() {
        // 接口限流
        String script = "if(redis.call('exists',KEYS[1] == 0))";
    }
}
