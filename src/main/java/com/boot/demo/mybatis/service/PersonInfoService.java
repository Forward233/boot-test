package com.boot.demo.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.boot.demo.mybatis.entity.Person;
import com.boot.demo.mybatis.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: yhl
 * @DateTime: 2020/6/23 13:35
 * @Description:
 */
@Service
public class PersonInfoService {

    @Autowired
    private PersonMapper personMapper;

    @Transactional(rollbackFor = Exception.class)
    public void update() {
        updatePerson();
        Person person = getPersonById(2L);
        System.out.println(person);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void updatePerson() {
        LambdaUpdateWrapper<Person> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Person::getAge, 25).eq(Person::getId, 2);
        personMapper.update(null, updateWrapper);
        int i = 1 / 0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Person getPersonById(Long id) {
        return personMapper.selectById(id);
    }
}
