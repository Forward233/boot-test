package com.boot.demo.mq.rabbitmq.queuemode.simple;

/**
 * @author: yhl
 * @DateTime: 2020/9/9 17:17
 * @Description:
 */
public class Producer {

    /** 队列名称 */
    private static final String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        /** 1.获取连接 */
        Connection newConnection = MQConnectionUtils.newConnection();
        /** 2.创建通道 */
        Channel channel = newConnection.createChannel();
        /** 3.创建队列声明 */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String msg = "我是生产者生成的消息";
        System.out.println("生产者发送消息:" + msg);
        /** 4.发送消息 */
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        channel.close();
        newConnection.close();
    }
}
