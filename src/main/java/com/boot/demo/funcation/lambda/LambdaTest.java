package com.boot.demo.funcation.lambda;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: yhl
 * @DateTime: 2020/9/21 5:51
 * @Description:
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<Goods> goods = Lists.newArrayList();
        goods.add(new Goods(1, "a",Lists.newArrayList("a","aa")));
        goods.add(new Goods(2, "b", Lists.newArrayList("b", "bb")));
        List<String> stringList = goods.stream().flatMap(g -> g.getLabels().stream()).collect(Collectors.toList());
        System.out.println(stringList);
        // 转换为数组
        Goods[] goods1 = goods.stream().toArray(Goods[]::new);
        // 拼接字符串
        String collect = goods.stream().map(Goods::getName).collect(Collectors.joining(","));
        System.out.println(collect);
//        第一个参数Goods::getId 表示选择Goods的getId作为map的key值；
//        第二个参数v -> v表示选择将原来的对象作为map的value值；
//        第三个参数(v1, v2) -> v1中，如果v1与v2的key值相同，选择v1作为那个key所对应的value值
        Map<Integer, Goods> goodsMap = goods.stream().collect(Collectors.toMap(Goods::getId, g -> g, (k1, k2) -> k1));


        String[] words = new String[]{"Hello","World"};
        List<String> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        a.forEach(System.out::print);

        Stream.of(words).map(i -> i.split("")).flatMap(Stream::of).collect(Collectors.toList());
        Stream.of(words).flatMap(word -> Stream.of(word.split(""))).collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    static class Goods{
        private Integer id;
        private String name;
        private List<String> labels;
    }
}

