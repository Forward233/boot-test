package com.boot.demo.base.clone;

import com.boot.demo.mybatis.entity.Person;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

/**
 * @author: yhl
 * @DateTime: 2021/1/24 2:47
 * @Description:
 */
public class CloneTest {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Person person = new Person();
        person.setId(0L);
        person.setFirstName("y");
        person.setLastName("hl");
        person.setAge(26);
        person.setDateOfBirth("");
        person.setPlaceOfBirth("");
        System.out.println("old:" + person);
        Person newPerson = (Person)BeanUtilsBean.getInstance().cloneBean(person);
        System.out.println("new" + newPerson);
        newPerson.setAge(27);
        System.out.println("old after update new:" + person);
    }
}
