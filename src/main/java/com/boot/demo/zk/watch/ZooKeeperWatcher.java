package com.boot.demo.zk.watch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author: yhl
 * @DateTime: 2021/1/25 14:09
 * @Description:
 */
public class ZooKeeperWatcher {

    public static final int SESSION_TIMEOUT = 10000;
    public static final String CONNECTION_ADDR = "192.168.1.200:2181";
    public ZooKeeper zk = null;
    private CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public void createConnection(String connectAddr, int sessionTimeout) {
        this.releaseConnection();
        try {
            // 第一步是连接zookeeper
            zk = new ZooKeeper(connectAddr, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if(Event.EventType.None.getIntValue() == event.getType().getIntValue()) {
                        connectedSemaphore.countDown();
                    }
                }
            });
            connectedSemaphore.await();
            // 第二步是通过getChildren方法递归设置Watcher监听，除了getChildren还有好多方法也可以。
            setWatch("/", zk);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void releaseConnection() {
        if (this.zk != null) {
            try {
                this.zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 递归创建监听
    public void setWatch(String path, ZooKeeper zk) throws Exception {
        if(zk.exists(path, null) == null) {
            return;
        }
        System.out.println("---------setWatch---------" + path);
        List<String> children = zk.getChildren(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.getPath() + "       >>>>>>>>>" + event.getType().name());
                try {
                    setWatch(path, zk);// 每次监听消费后，需要重新增加Watcher
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        if(!path.endsWith("Dao")) {
            for(String c : children) {
                String subP = ("/".equals(path)?"":path) + "/" + c;
                setWatch(subP, zk);
            }
        }
    }

}
