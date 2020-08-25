package com.boot.demo.encrypt;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author: yhl
 * @DateTime: 2020/6/21 7:45
 * @Description:
 */
public class EncryptTest {

    public static void main(String[] args) throws UnsupportedEncodingException {

        byte[] bytes = DigestUtils.md5Digest("aasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".getBytes());
        System.out.println(bytes.length);
        System.out.println(new String(bytes, "ios"));
    }
}
