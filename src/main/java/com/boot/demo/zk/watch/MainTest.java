package com.boot.demo.zk.watch;

import java.util.concurrent.Executors;

/**
 * @author: yhl
 * @DateTime: 2021/1/25 14:08
 * @Description:
 */
public class MainTest {

    public static void main(String[] args) {
        Executors.newSingleThreadExecutor().execute(() -> {
            ZooKeeperWatcher watcher = new ZooKeeperWatcher();
            watcher.createConnection(ZooKeeperWatcher.CONNECTION_ADDR, ZooKeeperWatcher.SESSION_TIMEOUT);
            while (true) {
            }
        });
    }
}
