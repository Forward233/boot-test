package com.boot.demo.base.introspector;

import lombok.Data;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: yhl
 * @DateTime: 2021/1/19 11:16
 * @Description:
 */

@Data
public class IntrospectorDemo {

    private String name;

    private int age;

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        IntrospectorDemo demo = new IntrospectorDemo();
        demo.setName("yhl");
        demo.setAge(26);
        BeanInfo beanInfo = Introspector.getBeanInfo(demo.getClass(), Object.class);
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            System.out.println(propertyDescriptor.getName()+"="+propertyDescriptor.getReadMethod().invoke(demo, (Object) null));
        }
    }
}
