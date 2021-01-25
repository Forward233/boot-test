package com.boot.demo.zk.watch;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author: yhl
 * @DateTime: 2021/1/25 14:35
 * @Description:
 */
public class WatcherDemo {

    public static void main(String[] args) {

        try {
            final CountDownLatch countDownLatch=new CountDownLatch(1);
            //watch机制(回调)，监听是否连接成功，ZooKeeper的构造函数中完成客户端连接
            final ZooKeeper zooKeeper = new ZooKeeper("192.168.9.163:2181", 4000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    //初次进入，此处会监听到事件
                    System.out.println("默认事件:"+watchedEvent.getType());
                    if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
                        //如果受收到了服务端的响应事件，连接成功
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();

            //只可以通过getData、Exists、getChildren这三个操作，来完成事件绑定
            Stat stat = zooKeeper.exists("/zk-lzb", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("监听节点:"+watchedEvent.getPath() + "   监听类型:"+watchedEvent.getType());
                }
            });

            zooKeeper.delete("/zk-lzb",stat.getVersion());

            //创建节点
            zooKeeper.create("/zk-lzb", "11".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            //此处会被监听到
            stat = zooKeeper.setData("/zk-lzb", "lzb".getBytes(), stat.getVersion());
            //因为watcher是一次性的操作,所以此处不会监听
            zooKeeper.delete("/zk-lzb",stat.getVersion());

            zooKeeper.exists("/zk-lzb-2", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("监听节点:"+watchedEvent.getPath() + "   监听类型:"+watchedEvent.getType());
                }
            });
            zooKeeper.create("/zk-lzb-2", "11".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

}
