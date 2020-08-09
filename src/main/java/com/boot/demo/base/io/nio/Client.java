package com.boot.demo.base.io.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author: yhl
 * @DateTime: 2020/8/10 5:04
 * @Description:
 */
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1", 9090));
            new Thread(() ->{
                while (true) {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        byte[] bytes = new byte[1024];
                        inputStream.read(bytes);
                        System.out.println(new String(bytes, StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            sendMsg(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMsg(Socket socket) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String msg = scanner.next();
            try {
                socket.getOutputStream().write(msg.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
