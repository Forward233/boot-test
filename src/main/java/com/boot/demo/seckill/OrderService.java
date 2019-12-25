package com.boot.demo.seckill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * Author: yhl
 * DateTime: 2019/12/24 22:08
 * Description:
 * [高并发场景-订单库存防止超卖_地藏Kelvin-CSDN博客]
 * (https://blog.csdn.net/kelvin_cai/article/details/102885656)
 */
@Service
public class OrderService {

    @Autowired
    StringRedisTemplate redisTemplate;


    public boolean order(OrderReq orderReq) {
        String key = "product:" + orderReq.getProductId();
        String num = redisTemplate.opsForValue().get(key);
        if (num == null) {
            // select num from product where id = ?
            num = "0";
            redisTemplate.opsForValue().setIfAbsent(key, num, Duration.ofMinutes(3));
        }
        Integer buyNum = orderReq.getBuyNum();
        if (Integer.parseInt(num) < buyNum) {
            return false;
        }

        // 超库存或者库存为0
        if (redisTemplate.opsForValue().decrement(key, buyNum) <= 0) {
            return false;
        }

        //下单，数据库写一条下单记录

        return true;
    }

}
