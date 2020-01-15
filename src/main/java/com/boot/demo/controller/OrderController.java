package com.boot.demo.controller;

import com.boot.demo.seckill.OrderReq;
import com.boot.demo.seckill.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yhl
 * @DateTime: 2019/12/25 16:30
 * @Description:
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order")
    public void orderProduct() {
        OrderReq orderReq = new OrderReq();
        orderReq.setProductId(1);
        orderReq.setBuyNum(1);
        orderService.orderWithDecr(orderReq);
    }

}
