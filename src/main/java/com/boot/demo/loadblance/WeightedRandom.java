package com.boot.demo.loadblance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: yhl
 * @DateTime: 2020/5/8 10:50
 * @Description:
 */
public class WeightedRandom {

    private static List<Atom> atomList;

    static {
        atomList = new ArrayList<>();
        atomList.add(new Atom("baidu.com", 30));
        atomList.add(new Atom("360.com", 20));
        atomList.add(new Atom("taobao.com", 40));
        atomList.add(new Atom("mi.com", 10));
    }

    /**
     * 1.分段
     * @return
     */
    public static Atom getWeightedRandomAtom2() {
        if (atomList.isEmpty()) {
            return null;
        }
        int weightSum = atomList.stream().mapToInt(Atom::getWeight).sum();
        int random  = new Random().nextInt(weightSum);
        for (Atom atom : atomList) {
            random -= atom.getWeight();
            if (random < 0) {
                return atom;
            }
        }
        //     * 2.将server根据权重放入list，new Random().nextInt(weightSum)获取索引
//        List<Atom> atoms = Lists.newArrayList();
//        for (Atom atom : atomList) {
//            for (int i = 0; i < atom.getWeight(); i++) {
//                atoms.add(atom);
//            }
//        }
//        return atoms.get(random);
        return null;
    }
}
