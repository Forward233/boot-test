package com.boot.demo.base.proxy.inject_bean2.service.impl;

import com.boot.demo.base.proxy.inject_bean2.service.ISystemService;
import com.boot.demo.base.proxy.inject_bean2.MyInterceptor;
import com.boot.demo.base.proxy.inject_bean2.annotation.Proxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yhl
 * @DateTime: 2020/6/26 10:19
 * @Description:
 */
@Slf4j
@Service
// 标识该类为动态代理类
@Proxy(value = "systemService" ,interceptor = MyInterceptor.class)
public class SystemServiceImpl implements ISystemService {

    @Autowired
    private RoleService roleService;

    @Override
    public int saveRecord() {
        log.info("saveRecord execute...");
        roleService.saveRole();
        return 0;
    }
}