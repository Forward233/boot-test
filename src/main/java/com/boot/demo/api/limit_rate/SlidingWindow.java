package com.boot.demo.api.limit_rate;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: yhl
 * @DateTime: 2020/7/6 6:50
 * @Description:
 * [【限流算法】java实现滑动时间窗口算法_DreamSeeker-CSDN博客_滑动时间窗口算法](https://blog.csdn.net/king0406/article/details/103129786)
 */
@Slf4j
public class SlidingWindow {

    private long limit = 15L;

    private LoadingCache<Long, AtomicLong> counter =
            CacheBuilder.newBuilder()
                    .expireAfterWrite(10, TimeUnit.SECONDS)
                    .build(new CacheLoader<Long, AtomicLong>() {
                        @Override
                        public AtomicLong load(Long key) throws Exception {
                            return new AtomicLong(0);
                        }
                    });

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    public void slideWindow() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                try {
                    long time = System.currentTimeMillis() / 1000;
                    //每秒发送随机数量的请求
                    int reqs = (int) (Math.random() * 5) + 1;
                    counter.get(time).addAndGet(reqs);
                    long nums = 0;
                    // time windows 5 s
                    for (int i = 0; i < 5; i++) {
                        nums += counter.get(time - i).get();
                    }
                    log.info("time=" + time + ",nums=" + nums);
                    if (nums > limit) {
                        log.info("限流了,nums=" + nums);
                    }
                } catch (Exception e) {
                    log.error("slideWindow error", e);
                } finally {
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, 5, 1, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        new SlidingWindow().slideWindow();
    }
}
