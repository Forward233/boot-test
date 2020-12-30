package com.boot.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: yhl
 * @DateTime: 2020/12/30 16:59
 * @Description:
 */
@Slf4j
public class NettyServer {

    private Integer port = 8085;
    /**
     * 启动Netty Server
     *
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        // 创建对应的 线程池
        // 创建Boss group
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        // 创建 workgroup
        EventLoopGroup workGroup = new NioEventLoopGroup();
        // 创建对应的启动类
        ServerBootstrap bootstrap = new ServerBootstrap();
        try{
            // 设置相关的配置信息
            bootstrap.group(boosGroup,workGroup) // 设置对应的线程组
                    .channel(NioServerSocketChannel.class) // 设置对应的通道
                    .option(ChannelOption.SO_BACKLOG,1024) // 设置线程的连接个数
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 设置
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("服务启动了....");
            // 绑定端口  启动服务
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            log.error("Netty启动异常");
        }finally {
            // 优雅停服
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NettyServer server = new NettyServer();
        server.start();
    }
}
