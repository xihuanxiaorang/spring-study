package top.xiaorang.spring.annotation.controller;

import org.springframework.stereotype.Controller;
import top.xiaorang.spring.annotation.service.OrderService;

import javax.annotation.Resource;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">...</a>
 * @Copyright 博客：<a href="https://xiaorang.top">...</a>  - show me the code
 * @since 2022/8/29 4:32
 */
@Controller
public class OrderController {
    @Resource
    private OrderService orderService;

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String toString() {
        return "OrderController{" +
                "orderService=" + orderService +
                '}';
    }
}
