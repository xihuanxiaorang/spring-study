package top.xiaorang.spring.annotation.bean;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">...</a>
 * @Copyright 博客：<a href="https://xiaorang.top">...</a>  - show me the code
 * @since 2022/8/29 0:36
 */
public class Car {
    public Car() {
        System.out.println("car... constructor...");
    }

    public void init() {
        System.out.println("car... init...");
    }

    public void destroy() {
        System.out.println("car... destroy...");
    }
}
