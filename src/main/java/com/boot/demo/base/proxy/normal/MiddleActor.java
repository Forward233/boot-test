package com.boot.demo.base.proxy.normal;

import lombok.Data;

/**
 * @author: yhl
 * @DateTime: 2020/3/9 16:00
 * @Description:
 */
@Data
public class MiddleActor implements IActor {

    private String name = "middle ";

    /**
     * @param money
     */
    @Override
    public void act(float money) {
        System.out.println("拿到" + money + "钱，开始中级表演");
    }

    /**
     * @param money
     */
    @Override
    public void sing(float money) {
        System.out.println("拿到" + money + "钱，开始中级演唱");
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
