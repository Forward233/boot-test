package com.boot.demo.jmh.benchmark;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * @author: yhl
 * @DateTime: 2020/4/4 9:22
 * @Description:
 */
public class StringConnectBenchmark {

    /**
     * 字符串拼接之 StringBuilder 基准测试
     */
    @Benchmark
    public void testStringBuilder() {
        print(new StringBuilder().append(1).append(2).append(3).toString());
    }

    /**
     * 字符串拼接之直接相加基准测试
     */
    @Benchmark
    public void testStringAdd() {
        print(new String()+ 1 + 2 + 3);
    }

    /**
     * 字符串拼接之String Concat基准测试
     */
    @Benchmark
    public void testStringConcat() {
        print(new String().concat("1").concat("2").concat("3"));
    }

    /**
     * 字符串拼接之 StringBuffer 基准测试
     */
    @Benchmark
    public void testStringBuffer() {
        print(new StringBuffer().append(1).append(2).append(3).toString());
    }

    /**
     * 字符串拼接之 StringFormat 基准测试
     */
    @Benchmark
    public void testStringFormat(){
        print(String.format("%s%s%s", 1, 2, 3));
    }

    public void print(String str) {

    }

}
