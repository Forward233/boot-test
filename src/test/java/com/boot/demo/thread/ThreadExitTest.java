package com.boot.demo.thread;

import com.boot.demo.DemoApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yhl
 * @DateTime: 2021/1/13 17:41
 * @Description:
 */
public class ThreadExitTest extends DemoApplicationTests {


    @Autowired
    ThreadPoolExecutor threadPoolExecutor;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Test
    void contextLoads() {
        for (int i = 0; i < 50; i++) {
            threadPoolExecutor.execute(() -> {
                int i1 = atomicInteger.incrementAndGet();
                System.out.println(i1);
            });
        }
    }

}
