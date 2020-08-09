package com.boot.demo.base.io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author: yhl
 * @DateTime: 2020/8/10 5:26
 * @Description:
 */
public class Handler implements Runnable{
    public static final int READING = 0, WRITING = 1;
    SocketChannel socketChannel;
    SelectionKey selectionKey;
    int state = READING;

    public Handler(SocketChannel socketChannel, SelectionKey selectionKey) {
        this.socketChannel = socketChannel;
        this.selectionKey = selectionKey;
    }


    @Override
    public void run() {
        try {
            if (state == READING) {
                read();
            } else {
                write();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void write() {
        try {
            socketChannel.write(ByteBuffer.wrap("你的消息我收到了".getBytes(StandardCharsets.UTF_8)));
            selectionKey.interestOps(READING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            socketChannel.read(byteBuffer);
            String message = new String(byteBuffer.array(), StandardCharsets.UTF_8);
            System.out.println(socketChannel.getRemoteAddress() + "发来的消息是:" + message);
            socketChannel.write(ByteBuffer.wrap("你的消息我收到了".getBytes(StandardCharsets.UTF_8)));
            selectionKey.interestOps(WRITING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
