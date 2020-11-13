package com.boot.demo.base.queue;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;
import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: yhl
 * @DateTime: 2020/3/5 16:15
 * @Description:
 */
@Data
public class DelayMessage implements Delayed {

    private final long delayTime; //延迟时间
    private final long expire;  //到期时间
    private String msg;   //发送消息

    public DelayMessage(long delay, String msg) {
        delayTime = delay;
        this.msg = msg;
        expire = System.currentTimeMillis() + delay;
    }

    /**
     * 剩余时间=到期时间-当前时间
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 优先队列里面优先级规则
     */
    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    public static void main(String[] args) {
//        BlockingQueue<DelayMessage> queue = new DelayQueue<DelayMessage>();
//        DelayMessage sendMsg = new DelayMessage(5000, "我很帅也很温柔1");//10秒后激活消息
//        DelayMessage sendMsg2 = new DelayMessage(10000, "我很帅也很温柔2");//10秒后激活消息
//        DelayMessage sendMsg3 = new DelayMessage(15000, "我很帅也很温柔3");//10秒后激活消息
//
//        queue.offer(sendMsg);//发送消息
//        queue.offer(sendMsg2);
//        queue.offer(sendMsg3);
//
//        DelayMessage receiverMsg = queue.take();//阻塞获取消息
//        System.out.println(receiverMsg.getMsg());
        final Timer timer = new HashedWheelTimer();

        AtomicReference<Thread> owner = new AtomicReference<Thread>();
        final Thread thread = Thread.currentThread();
        owner.compareAndSet(null, thread);
    }
}
