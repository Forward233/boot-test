package com.boot.demo.testrate;

/**
 * @author: yhl
 * @DateTime: 2020/4/4 12:00
 * @Description:
 */
public class TestRateWithVariable {

    public static void main(String[] args) {
        long sum = 0L ;
        Integer.valueOf(10000);
        int range = 8000;
        for(int i = 0 ;i < 10;i++){
            long beginTime = System.currentTimeMillis();
            for(int j  = 0 ;j < 1000000; j++){
                for(int k = 0 ;k < range; k ++){
                    Integer integer  = k ;
                }
            }
            long endTime = System.currentTimeMillis();
            sum = sum + (endTime - beginTime);
        }
        System.out.println("");
        System.out.println("总时间：" + sum + "s");
        System.out.println("");
    }
}
