package com.boot.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author: yhl
 * DateTime: 2019/12/18 20:35
 * Description: write some description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBean implements Serializable {
    private static final long serialVersionUID = -7762312493596281009L;
    private Long id;
    private String username;
}
