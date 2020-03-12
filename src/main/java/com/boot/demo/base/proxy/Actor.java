package com.boot.demo.base.proxy;

import lombok.Data;

/**
 * @author: yhl
 * @DateTime: 2020/3/9 16:00
 * @Description:
 */
@Data
public class Actor implements IActor{
    public void basicAct(float money){
        System.out.println("拿到"+money+"钱，开始初级表演");
    }
    public void advancedAct(float money){
        System.out.println("拿到"+money+"钱，开始高级表演");
    }

    private String name;
}
