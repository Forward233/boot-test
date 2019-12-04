package com.boot.demo.funcation;

/**
 * Author: yhl
 * DateTime: 2019/10/11 9:48
 * Description: write some description
 */
public class GreetingServiceTest {
    public static void main(String[] args) {
          //  匿名内部类
        GreetingService greetingServic = new GreetingService() {
            @Override
            public void syaMessage(String message) {
                System.out.println("hello" + message);
            }
        };
        greetingServic.syaMessage(" world");

        //lambda表达式
        final GreetingService greetingService = message -> System.out.println("world" + message);
        greetingService.syaMessage(" world");

    }
}
