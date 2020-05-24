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

    public static String s = "abc";
    public static void main(String[] args) {

        // 编译时放在常量池
        String s1 = "abc";
        String s2 = "a";
        // 运行时编译，放在堆中
        String s3 = s2 + "bc";
        System.out.println(s1 == s3);

        byte a = 127;
        byte b = 127;
        b = (byte) (a + b); // 报编译错误:cannot convert from int to byte
        b += a;
    }
}
