package top.xiaorang.spring.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">...</a>
 * @Copyright 博客：<a href="https://xiaorang.top">...</a>  - show me the code
 * @since 2022/8/29 4:01
 */
@Configuration
@ComponentScan({"top.xiaorang.spring.annotation.repository", "top.xiaorang.spring.annotation.service", "top.xiaorang.spring.annotation.controller"})
public class MainConfigOfAutowired {
}
