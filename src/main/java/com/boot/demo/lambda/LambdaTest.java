package com.boot.demo.lambda;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: yhl
 * @DateTime: 2020/9/21 5:51
 * @Description:
 */
public class LambdaTest {


    public static void main(String[] args) {
        LambdaTest.testFlatMap();
    }

    public static void testFlatMap() {
        List<Goods> goods = Lists.newArrayList();
        goods.add(new Goods(1, "a",Lists.newArrayList(new Labels(1,"al"),new Labels(2,"a2"))));
        goods.add(new Goods(2, "b", Lists.newArrayList(new Labels(1,"bl"),new Labels(1,"b2"))));
        // flatMap
        List<String> stringList = goods.stream().flatMap(g -> g.getLabels().stream()).map(Labels::getDesc).collect(Collectors.toList());
        System.out.println(stringList);
        // 转换为数组
        Goods[] goods1 = goods.stream().toArray(Goods[]::new);
        // 拼接字符串
        String collect = goods.stream().map(Goods::getName).collect(Collectors.joining(","));
        System.out.println(collect);
        // toMap
//        第一个参数Goods::getId 表示选择Goods的getId作为map的key值；
//        第二个参数v -> v表示选择将原来的对象作为map的value值；
//        第三个参数(v1, v2) -> v1中，如果v1与v2的key值相同，选择v1作为那个key所对应的value值
        Map<Integer, Goods> goodsMap = goods.stream().collect(Collectors.toMap(Goods::getId, g -> g, (k1, k2) -> k1));

        String[] words = new String[]{"Hello","World"};
        List<String> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Stream::of)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(a);

        List<String> collect1 = Stream.of(words).map(i -> i.split("")).flatMap(Stream::of).collect(Collectors.toList());
        System.out.println(collect1);
        List<String> collect2 = Stream.of(words).flatMap(word -> Stream.of(word.split(""))).collect(Collectors.toList());
        System.out.println(collect2);
    }

    public static void testReduce() {
        Optional accResult = Stream.of(1, 2, 3, 4)
                .reduce((acc, item) -> {
                    System.out.println("acc : "  + acc);
                    System.out.println("item: " + item);
                    System.out.println("--------");
                    return 0;
                });
    }


    @Data
    @AllArgsConstructor
    static class Goods{
        private Integer id;
        private String name;
        private List<Labels> labels;
    }

    @Data
    @AllArgsConstructor
    static class Labels{
        private Integer id;
        private String desc;
    }
}

