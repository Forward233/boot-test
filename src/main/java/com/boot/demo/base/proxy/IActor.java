package com.boot.demo.base.proxy;

/**
 * @author: yhl
 * @DateTime: 2020/3/9 16:01
 * @Description:
 */
public interface IActor {
    void basicAct(float money);
    void advancedAct(float money);

    void setName(String name);
}
