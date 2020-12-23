package com.boot.demo.monitor.jmx;

/**
 * @author: yhl
 * @DateTime: 2020/9/23 6:46
 * @Description:
 */
public class App implements AppMBean{
    @Override
    public void welcome() {
        System.out.println("Welcome jmx 初体验...");
    }
}
