package com.boot.demo.seckill;

import lombok.Data;

import java.io.Serializable;

/**
 * Author: yhl
 * DateTime: 2019/12/24 22:10
 * Description: write some description
 */
@Data
public class OrderReq implements Serializable {

    private static final long serialVersionUID = 3869674230814433230L;

    private Integer productId;

    private Integer buyNum;
}
