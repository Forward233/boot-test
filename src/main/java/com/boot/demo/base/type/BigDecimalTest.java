package com.boot.demo.base.type;

import java.math.BigDecimal;

/**
 * @author: yhl
 * @DateTime: 2021/1/10 9:35
 * @Description:
 */
public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal decimal1 = new BigDecimal("1.2");
        BigDecimal decimal2 = new BigDecimal("1.2");
        BigDecimal bigDecimal = decimal1.multiply(decimal2).setScale(2, BigDecimal.ROUND_CEILING);

    }


}
