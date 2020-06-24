package com.boot.demo.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.demo.mybatis.entity.Person;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: yhl
 * @DateTime: 2020/6/23 13:31
 * @Description:
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

}
