package top.xiaorang.spring.annotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import top.xiaorang.spring.annotation.bean.Person;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">...</a>
 * @Copyright 博客：<a href="https://xiaorang.top">...</a>  - show me the code
 * @since 2022/8/29 18:10
 */
@Configuration
public class MainConfigOfProfile {
    @Profile({"dev", "default"})
    @Bean
    public Person person1() {
        return new Person("张三", 18);
    }

    @Profile("test")
    @Bean
    public Person person2() {
        return new Person("李四", 23);
    }

    @Profile("prod")
    @Bean
    public Person person3() {
        return new Person("王五", 30);
    }
}
