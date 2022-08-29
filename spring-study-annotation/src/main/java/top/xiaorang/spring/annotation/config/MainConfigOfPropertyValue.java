package top.xiaorang.spring.annotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import top.xiaorang.spring.annotation.bean.Teacher;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">...</a>
 * @Copyright 博客：<a href="https://xiaorang.top">...</a>  - show me the code
 * @since 2022/8/29 2:27
 */
@Configuration
@PropertySource({"classpath:teacher.properties"})
public class MainConfigOfPropertyValue {
    @Bean
    public Teacher teacher() {
        return new Teacher();
    }
}
