package com.boot.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yhl
 * @DateTime: 2020/5/3 16:44
 * @Description:
 */
@Service
public class PersonService {
    @Autowired
    PersonDao personDao;
}
