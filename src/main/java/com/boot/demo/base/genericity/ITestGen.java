package com.boot.demo.base.genericity;

/**
 * @author: yhl
 * @DateTime: 2020/1/8 10:13
 * @Description:
 */
public interface ITestGen<T> {

    <V> V get(Class<V> clasz) throws IllegalAccessException, InstantiationException;

    <V> V getV(V v);

    T getT(T t);
}
