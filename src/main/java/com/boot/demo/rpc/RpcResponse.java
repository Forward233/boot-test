package com.boot.demo.rpc;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: yhl
 * @DateTime: 2020/6/5 12:46
 * @Description:
 */
@Data
@ToString
public class RpcResponse implements Serializable {
    private static final long serialVersionUID = -1383587821897428941L;

    private Throwable error;
    private Object result;
}
