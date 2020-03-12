package com.boot.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: yhl
 * @DateTime: 2020/3/12 16:19
 * @Description:
 */
@Data
@Accessors(chain = true)
public class ResponseData<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    public ResponseData() {
    }

    public ResponseData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseData(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseData<T> success(T data) {
        return new ResponseData(-1, "", data);
    }

    public static <T> ResponseData<T> success(T data, String message) {
        return new ResponseData(-1, message, data);
    }

    public static <T> ResponseData<T> fail(int code, String msg) {
        return new ResponseData(code, msg, null);
    }

    public static <T> ResponseData<T> fail(String msg) {
        return new ResponseData(0, msg, null);
    }

}
