package top.xiaorang.spring.annotation;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.xiaorang.spring.annotation.controller.BookController;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">...</a>
 * @Copyright 博客：<a href="https://xiaorang.top">...</a>  - show me the code
 * @since 2022/8/27 16:32
 */
public class SpringXmlConfigurationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringXmlConfigurationTest.class);

    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        BookController bookController = applicationContext.getBean(BookController.class);
        LOGGER.info("\n获取到的bean实例：{}", bookController);
    }
}
