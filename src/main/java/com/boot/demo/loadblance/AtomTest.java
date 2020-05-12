package com.boot.demo.loadblance;

import com.alibaba.fastjson.JSON;

/**
 * @author: yhl
 * @DateTime: 2020/5/8 11:00
 * @Description:
 */
public class AtomTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final Atom weightedRandomAtom = WeightedRandom.getWeightedRandomAtom2();
            System.out.println(JSON.toJSONString(weightedRandomAtom));
        }
    }
}
