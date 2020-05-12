package com.boot.demo.loadblance;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: yhl
 * @DateTime: 2020/5/8 10:50
 * @Description:
 */
@Data
@AllArgsConstructor
public class Atom {
    private String key;
    private int weight;
}
