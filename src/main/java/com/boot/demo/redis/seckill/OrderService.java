package com.boot.demo.redis.seckill;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: yhl
 * DateTime: 2019/12/24 22:08
 * Description:
 * [高并发场景-订单库存防止超卖_地藏Kelvin-CSDN博客]
 * (https://blog.csdn.net/kelvin_cai/article/details/102885656)
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    StringRedisTemplate redisTemplate;

    private AtomicInteger atomicInteger = new AtomicInteger();


    public void orderWithDecr(OrderReq orderReq) {

        // 前端对请求个数做计数，超过多少个请求之后，立即抢购按钮置灰。

        try {
            // 提前预热库存key到redis,以下可省略
            String key = "product:" + orderReq.getProductId();
            String num = redisTemplate.opsForValue().get(key);
            if (num == null) {
                // select num from product where id = ?
                // todo
                num = "1000";
                redisTemplate.opsForValue().setIfAbsent(key, num, Duration.ofMinutes(3));
            }

            // 购买数量
            Integer buyNum = orderReq.getBuyNum();
            if (Integer.parseInt(num) < buyNum) {
                log.info("库存不足，库存：{}，购买数量：{}", num, orderReq.getBuyNum());
                return;
            }

            // 假如瞬时并发为10000，但进入mysql执行减库存的逻辑只有库存个数个请求，所以大大降低了数据库的压力

            if (redisTemplate.opsForValue().decrement(key, buyNum) >= 0) {
            } else {
                // 补redis中库存
                final Long increment = redisTemplate.opsForValue().increment(key, buyNum);
            }
        } catch (Exception e) {
            log.info("-------------:{}", e.toString());
        }

    }

    public void orderWithWatch(OrderReq orderReq) {
        String key = "product:" + orderReq.getProductId();
        String num = redisTemplate.opsForValue().get(key);
        if (num == null) {
            // select num from product where id = ?
            // todo
            num = "0";
            redisTemplate.opsForValue().setIfAbsent(key, num, Duration.ofMinutes(3));
        }

        if (Integer.parseInt(num) > 0) {
            redisTemplate.watch(key);
            redisTemplate.multi();
            redisTemplate.opsForValue().decrement(key);
            final List<Object> exec = redisTemplate.exec();
            if (!exec.isEmpty()) {
                log.info("抢购成功，----------：{},抢了：{}个产品", orderReq, orderReq.getBuyNum());
            } else {
                log.info("抢购失败，----------：{}", orderReq);
            }
        }
    }
}
