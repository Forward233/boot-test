package com.boot.demo.spring.factory_bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author: yhl
 * @DateTime: 2020/7/16 8:03
 * @Description:
 */
@Component
public class ToolFactory implements FactoryBean<Tool> {

    @Override
    public Tool getObject() throws Exception {
        return new Tool(1);
    }

    @Override
    public Class<?> getObjectType() {
        return Tool.class;
    }
}
