package com.boot.demo.testrate;

/**
 * @author: yhl
 * @DateTime: 2020/4/4 18:36
 * @Description:
 */
public class ForTest {

    public static void main(String[] args) {
        long length = 1000000000L;
        final long l = System.currentTimeMillis();
        for (long i = 0; i < 1000000000L; i++) {
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}
