package com.boot.demo.funcation;

/**
 * @author dongsilin
 * @version 2018/8/31.
 * 回调业务逻辑
 */
public interface CallBackExecutor<R> {

	R execute() throws Throwable;
}
