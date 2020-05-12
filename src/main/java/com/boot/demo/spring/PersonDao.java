package com.boot.demo.spring;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author: yhl
 * @DateTime: 2020/5/3 16:43
 * @Description:
 */
@Component
@Data
public class PersonDao {

    private int anInt;

    public PersonDao(int a) {
        this.anInt = a;
    }
    public PersonDao() {
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public static void main(String[] args) {

        HashMap<Integer, PersonDao> hashMap = new HashMap<>();
        for (int i = 0; i < 17; i++) {
            hashMap.put(i, new PersonDao(1));
        }
    }
}
