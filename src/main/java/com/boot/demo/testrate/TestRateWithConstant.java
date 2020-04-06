package com.boot.demo.testrate;

import java.util.Date;

/**
 * [java循环长度的相同、循环体代码相同的两次for循环的执行时间相差了100倍? - 知乎](https://www.zhihu.com/question/58735131/answer/307771944)
 * @author: yhl
 * @DateTime: 2020/4/4 12:00
 * @Description: -XX:AutoBoxCacheMax=8000 -XX:+UnlockDiagnosticVMOptions -XX:+PrintCompilation -XX:+PrintInlining
 * -XX:-DoEscapeAnalysis
 *
 */
public class TestRateWithConstant {

    public static void main(String[] args) {
        long sum = 0L ;
//        Integer.valueOf(10000);
        int range = 8000;
        /**
         * -XX:AutoBoxCacheMax=8000
         * 如果循环条件 == 8000 运行慢，开启-XX:-DoEscapeAnalysis或new Date().getTime()换成System.currentTimeMillis();，运行快
         *            <> 8000  运行快
         */
        for(int i = 0 ;i < 10;i++){
//            long beginTime = System.currentTimeMillis();
            long beginTime = new Date().getTime();
            for(int j  = 0 ;j < 10000; j++){
                for(int k = 0 ;k < 8000; k ++){
                    /**
                     * 装箱
                     *   public static Integer valueOf(int i) {
                     *         if (i >= IntegerCache.low && i <= IntegerCache.high)
                     *             return IntegerCache.cache[i + (-IntegerCache.low)];
                     *         return new Integer(i);
                     *   }
                     */
                    Integer integer  = k ;
                }
            }
            long endTime =  System.currentTimeMillis();
//            long endTime =  new Date().getTime();
            sum = sum + (endTime - beginTime);
        }
        System.out.println("");
        System.out.println("总时间：" + sum + "ms");
        System.out.println("");
    }
}
