package com.boot.demo.redis;

import com.boot.demo.redis.seckill.OrderReq;
import com.boot.demo.redis.seckill.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: yhl
 * @DateTime: 2019/12/25 13:06
 * @Description:
 */
@SpringBootTest
class OrderServiceTest {

    @Qualifier("applicationTaskExecutor")
    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Test
    void orderWithDecr() {
    }

    @Test
    void orderWithWatch() {
        redisTemplate.opsForValue().set("product:1", "100");
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executorService.execute(() ->{
                OrderReq orderReq = new OrderReq();
                orderReq.setProductId(1);
                Random random = new Random();
//                orderReq.setBuyNum(random.nextInt(2) + 1);
                orderReq.setBuyNum(1);
                orderService.orderWithDecr(orderReq);
            });
        }
    }
}