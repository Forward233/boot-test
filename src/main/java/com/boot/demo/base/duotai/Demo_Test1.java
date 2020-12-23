package com.boot.demo.base.duotai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author: yhl
 * @DateTime: 2020/4/8 8:03
 * @Description: [JAVA的多态用几句话能直观的解释一下吗？ - 知乎](https://www.zhihu.com/question/30082151)
 */
class Demo_Test1 {

    @Autowired
    private ApplicationContext applicationContext;
    public static void main(String[] args) {
        Animal am = new Cat();
        am.eat();
        am.run();
    }
}