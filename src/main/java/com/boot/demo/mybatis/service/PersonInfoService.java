package com.boot.demo.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.boot.demo.mybatis.entity.Person;
import com.boot.demo.mybatis.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yhl
 * @DateTime: 2020/6/23 13:35
 * @Description:
 */
@Service
public class PersonInfoService {

    @Autowired
    private PersonMapper personMapper;

    public void updatePerson() {
        LambdaUpdateWrapper<Person> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Person::getAge, 15).eq(Person::getId, 2);
        final int update = personMapper.update(null, updateWrapper);
    }
}
