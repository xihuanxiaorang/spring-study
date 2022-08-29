package top.xiaorang.spring.annotation.service;

import org.springframework.stereotype.Service;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">...</a>
 * @Copyright 博客：<a href="https://xiaorang.top">...</a>  - show me the code
 * @since 2022/8/29 4:31
 */
@Service
public class OrderServiceImpl2 implements OrderService {
    private String label = "2";

    @Override
    public String toString() {
        return "OrderServiceImpl{" +
                "label='" + label + '\'' +
                '}';
    }
}
