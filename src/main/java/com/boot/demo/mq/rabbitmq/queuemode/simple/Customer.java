package com.boot.demo.mq.rabbitmq.queuemode.simple;

import com.boot.demo.mq.rabbitmq.util.MQConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: yhl
 * @DateTime: 2020/9/9 17:17
 * @Description:
 */
public class Customer {
    /** 队列名称 */
    private static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("002");
        /** 1.获取连接 */
        Connection newConnection = MQConnectionUtils.newConnection();
        /** 2.获取通道 */
        Channel channel = newConnection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msgString = new String(body, "UTF-8");
                System.out.println("消费者获取消息:" + msgString);
            }
        };
        /** 3.监听队列 */
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);

    }
}
