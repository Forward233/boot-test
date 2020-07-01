package com.boot.demo.spring.aop.proxy;

import com.boot.demo.spring.aop.ProxyBeanHolder;
import com.boot.demo.spring.aop.util.ConfigurationUtil;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author: yhl
 * @DateTime: 2020/7/1 19:17
 * @Description:
 */
public class CustomizedProxyInterceptor implements MethodInterceptor {

    private List<ProxyBeanHolder> proxyBeanHolderList;

    public CustomizedProxyInterceptor(List<ProxyBeanHolder> proxyBeanHolderList) {
        this.proxyBeanHolderList = proxyBeanHolderList;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        for (ProxyBeanHolder proxyBeanHolder : proxyBeanHolderList) {
            String annotationName = proxyBeanHolder.getAnnotationName();
            if (ConfigurationUtil.BEFORE.equals(annotationName) || ConfigurationUtil.AROUND.equals(annotationName)) {
                doProxy(proxyBeanHolder);
            }
        }

        return null;
    }

    private void doProxy(ProxyBeanHolder proxyBeanHolder) {
        String className = proxyBeanHolder.getClassName();
        String methodName = proxyBeanHolder.getMethodName();
        Object clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
