package com.boot.demo.base.duotai;

/**
 * @author: yhl
 * @DateTime: 2020/4/8 8:03
 * @Description:
 * [JAVA的多态用几句话能直观的解释一下吗？ - 知乎](https://www.zhihu.com/question/30082151)
 */
class Demo_Test1 {
    public static void main(String[] args) {
        Animal am = new Cat();
        am.eat();
        am.sleep();
        am.run();
        //这里先注释掉，等会会说明
        ((Cat) am).catchMouse();
        //System.out.println(am.name);//这里先注释，待会说明
        System.out.println(am.num);
        System.out.println(am.age);
    }
}