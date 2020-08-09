package com.boot.demo.base.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author: yhl
 * @DateTime: 2020/8/10 5:14
 * @Description:
 */
@Slf4j
public class Acceptor implements Runnable {

    ServerSocketChannel serverSocketChannel;
    Selector selector;

    public Acceptor(ServerSocketChannel serverSocketChannel, Selector selector) {
        this.serverSocketChannel = serverSocketChannel;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            log.info("有客户端{}连接上来了," ,socketChannel.getRemoteAddress());
            socketChannel.configureBlocking(false);
            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
            selectionKey.attach(new Handler(socketChannel, selectionKey));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
