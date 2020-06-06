package com.boot.demo.rpc.server;

/**
 * @author: yhl
 * @DateTime: 2020/6/6 17:19
 * @Description:
 */
public class ServerStart {
    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.start(8088, "com.boot.demo.rpc.server.service");
    }
}
