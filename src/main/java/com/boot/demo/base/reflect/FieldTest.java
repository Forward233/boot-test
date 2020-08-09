package com.boot.demo.base.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yhl
 * @DateTime: 2020/7/17 7:05
 * @Description:
 */
public class FieldTest {

    public Map
            <Integer, String> map = new HashMap<>();

    public static void main(String[] args) throws NoSuchFieldException {
        Field map = FieldTest.class.getDeclaredField("map");
        System.out.println(map.getType());
        System.out.println(map.getGenericType());
        ParameterizedType parameterizedType = (ParameterizedType) map.getGenericType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        List<Type> types = Arrays.asList(actualTypeArguments);
        System.out.println(types);
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println(actualTypeArgument.getTypeName());
        }
    }


}
