package top.xiaorang.spring.annotation;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.xiaorang.spring.annotation.bean.Person;
import top.xiaorang.spring.annotation.config.MainConfig;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">...</a>
 * @Copyright 博客：<a href="https://xiaorang.top">...</a>  - show me the code
 * @since 2022/8/27 16:32
 */
public class SpringAnnotationConfigurationOfScopeTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringAnnotationConfigurationOfScopeTest.class);

    @Test
    public void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        LOGGER.info("容器启动之后，获取Person组件之前");
        Person person = applicationContext.getBean(Person.class);
        Person person2 = applicationContext.getBean(Person.class);
        LOGGER.info("是否同一个person实例？{}", person == person2);
    }
}
