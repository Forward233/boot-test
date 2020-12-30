package com.boot.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author: yhl
 * @DateTime: 2020/12/30 16:51
 * @Description:
 */
@Service("nettyClient")
@Slf4j
public class NettyClient {

    private EventLoopGroup loop = new NioEventLoopGroup();


    public void run() {
        doConnect(new Bootstrap(), loop);
    }

    /**
     * netty client 连接，连接失败10秒后重试连接
     */
    public void doConnect(Bootstrap bootstrap, EventLoopGroup eventLoopGroup) {
        try {
            if (bootstrap != null) {
                bootstrap.group(eventLoopGroup);
                bootstrap.channel(NioSocketChannel.class);
                bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
                bootstrap.handler(new NettyClientHandler());
                bootstrap.remoteAddress("localhost", 8085);
                bootstrap.connect().addListener((ChannelFuture futureListener) -> {
                    final EventLoop eventLoop = futureListener.channel().eventLoop();
                    if (!futureListener.isSuccess()) {
                        log.warn("客户端已启动，与服务端建立连接失败,10s之后尝试重连!");
                        eventLoop.schedule(() -> doConnect(new Bootstrap(), eventLoop), 10, TimeUnit.SECONDS);
                    } else {
                        log.info("客户端已启动成功,port:{}，开始登录服务端", 8085);
                        futureListener.channel().writeAndFlush(Unpooled.copiedBuffer("msg!!!", CharsetUtil.UTF_8));
                    }
                });
            }
        } catch (Exception e) {
            log.error("连接服务端失败,error：" + e);
        }finally {
            System.out.println("---------finally---------");
        }
    }

    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        client.run();
    }
}
