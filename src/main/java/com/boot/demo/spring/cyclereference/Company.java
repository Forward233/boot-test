package com.boot.demo.spring.cyclereference;

/**
 * @author: yhl
 * @DateTime: 2020/8/25 11:18
 * @Description:
 */
public class Company {

    private Boss boss;

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }
}
