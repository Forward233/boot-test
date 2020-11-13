package com.boot.demo.base.genericity;

/**
 * @author: yhl
 * @DateTime: 2020/1/8 10:17
 * @Description:
 */
public class TestGen implements ITestGen<String> {

    @Override
    public <V> V get(Class<V> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    @Override
    public <V> V getV(V v) {
        return null;
    }

    @Override
    public String getT(String string) {
        return null;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        TestGen testGen = new TestGen();
        final String s = testGen.get(String.class);
        Long aLong = 1L;
        Long v = testGen.getV(aLong);
        Integer integer = testGen.get(Integer.class);
    }

}
