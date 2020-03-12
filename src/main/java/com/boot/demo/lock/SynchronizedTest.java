package com.boot.demo.lock;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: yhl
 * @DateTime: 2020/3/6 7:49
 * @Description:
 * https://blog.csdn.net/weixin_40616523/article/details/87883267
 */
public class SynchronizedTest {

    private final Object test = new Object();

    static int i = 10;

    public synchronized void obj1() {
        synchronized (this) {
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }
    }


    public static synchronized void obj2() {
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
            }
        }
    }

    public synchronized void obj3() {
        synchronized (this) {
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }
    }

    public static void main(String[] args) {
        final SynchronizedTest synchronizedTest = new SynchronizedTest();
        final SynchronizedTest synchronizedTest1 = new SynchronizedTest();
        new Thread(synchronizedTest::obj1).start();
        new Thread(synchronizedTest::obj3).start();

        JSONObject res = new JSONObject();
        res.put("code", -1);
        res.put("msg", "请登录");

    }
}
