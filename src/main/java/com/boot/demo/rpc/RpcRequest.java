package com.boot.demo.rpc;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: yhl
 * @DateTime: 2020/6/5 12:37
 * @Description:
 */
@Data
@ToString
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 6850035403302321542L;

    private String className;
    private String methodName;
    private Class<?>[] paramTypes;
    private Object[] params;
}
