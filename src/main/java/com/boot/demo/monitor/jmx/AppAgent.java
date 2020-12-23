package com.boot.demo.monitor.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @author: yhl
 * @DateTime: 2020/9/23 6:46
 * @Description:
 */
public class AppAgent {
    public static void main(String[] args) throws Exception {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        App mbean = new App();
        ObjectName objectName = new ObjectName("com.boot.demo.monitor.jmx:type=App");
        platformMBeanServer.registerMBean(mbean,objectName);
        System.out.println("wait forever...");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
