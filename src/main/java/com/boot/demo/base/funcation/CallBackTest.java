package com.boot.demo.base.funcation;

/**
 * Author: yhl
 * DateTime: 2019/9/27 15:23
 * Description: write some description
 */
public class CallBackTest<R> {

    public void callbackTest(CallBackExecutor<R> success, CallBackExecutor<R> failure) throws Throwable {
        System.out.println(success.execute());
        System.out.println(failure.execute());
    }


    public static void main(String[] args) throws Throwable {
        final CallBackTest<Object> callBackTest = new CallBackTest<>();
        callBackTest.callbackTest(CallBackTest::successTest, () -> "BBB");
    }

    private static String successTest() {
        return "22";
    }
}
