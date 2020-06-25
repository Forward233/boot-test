package com.boot.demo.base.proxy.normal;

import lombok.Data;

/**
 * @author: yhl
 * @DateTime: 2020/3/9 16:00
 * @Description:
 */
@Data
public class PrimaryActor implements IActor {

    private String name = "primary ";

    /**
     * @param money
     */
    @Override
    public void act(float money) {
        System.out.println("拿到" + money + "钱，开始初级表演");
    }

    /**
     * @param money
     */
    @Override
    public void sing(float money) {
        System.out.println("拿到" + money + "钱，开始初级演唱");
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
