package top.xiaorang.spring.annotation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import top.xiaorang.spring.annotation.bean.Color;
import top.xiaorang.spring.annotation.bean.Person;
import top.xiaorang.spring.annotation.condition.LinuxCondition;
import top.xiaorang.spring.annotation.condition.WindowsCondition;
import top.xiaorang.spring.annotation.repository.BookRepository;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">...</a>
 * @Copyright 博客：<a href="https://xiaorang.top">...</a>  - show me the code
 * @since 2022/8/27 17:59
 */
@Configuration
@ComponentScan(value = "top.xiaorang.spring.annotation", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Service.class})
}, useDefaultFilters = false)
@ComponentScan(value = "top.xiaorang.spring.annotation", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookRepository.class})
}, useDefaultFilters = false)
@Import({Color.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainConfig.class);

    @Lazy
    @Bean
    public Person person() {
        LOGGER.info("向Spring容器中添加Person组件");
        return new Person("小让", 27);
    }

    @Conditional(WindowsCondition.class)
    @Bean("bill")
    public Person person1() {
        LOGGER.info("向Spring容器中添加Person（bill）组件");
        return new Person("bill", 65);
    }

    @Conditional(LinuxCondition.class)
    @Bean("linus")
    public Person person2() {
        LOGGER.info("向Spring容器中添加Person（linus）组件");
        return new Person("linus", 50);
    }
}
