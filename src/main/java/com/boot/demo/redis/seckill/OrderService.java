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


    /**
     * 并发有问题，用lua脚本实现
     * @param orderReq
     */
    public void orderWithDecr(OrderReq orderReq) {

        // 前端对请求个数做计数，超过多少个请求之后，立即抢购按钮置灰。
        try {
            // 提前预热库存key到redis,以下可省略
            String key = "product:" + orderReq.getProductId();
            String num = redisTemplate.opsForValue().get(key);
            if (num == null || Integer.parseInt(num) <= 0) {
                // select num from product where id = ?
                // todo
                num = "1000";
                redisTemplate.opsForValue().setIfAbsent(key, num);
            }

            // 先进行库存判断
            Integer buyNum = orderReq.getBuyNum();
            if (Integer.parseInt(num) < buyNum) {
                log.info("库存不足，库存：{}，购买数量：{}", num, orderReq.getBuyNum());
                return;
            }

            // t0 正在准备执行decrement，执行完num:3
            // 在t执行decrement之前，来了3个线程  t1:4 t2:2 t3:6,走到了decrement，此时t0执行完了decrement
            // 假如在t1未进行补库存时，t2满足下单要求，但是下单失败。

            // 假如瞬时并发为10000，但进入mysql执行减库存的逻辑只有库存个数个请求，所以大大降低了数据库的压力
            if (redisTemplate.opsForValue().decrement(key, buyNum) >= 0) {
                // 下单
            } else {
                // 补redis中库存
                final Long increment = redisTemplate.opsForValue().increment(key, buyNum);
                // 返回下单失败
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
            // 不适合高并发，如果key的值被改变，会有很多抢购失败，
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
