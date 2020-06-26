package com.boot.demo.base.proxy.inject_bean2.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: yhl
 * @DateTime: 2020/6/26 10:20
 * @Description:
 */
@Slf4j
@Service
public class RoleService {
    public void saveRole() {
        log.info("保存角色信息");
    }
}
