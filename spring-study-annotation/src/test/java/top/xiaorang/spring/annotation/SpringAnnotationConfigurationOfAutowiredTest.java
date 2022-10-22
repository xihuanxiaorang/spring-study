package top.xiaorang.spring.annotation;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.xiaorang.spring.annotation.config.MainConfigOfAutowired;
import top.xiaorang.spring.annotation.controller.BookController;
import top.xiaorang.spring.annotation.controller.OrderController;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">...</a>
 * @Copyright 博客：<a href="https://xiaorang.top">...</a>  - show me the code
 * @since 2022/8/27 16:32
 */
public class SpringAnnotationConfigurationOfAutowiredTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringAnnotationConfigurationOfAutowiredTest.class);

    @Test
    public void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        BookController bookController = applicationContext.getBean(BookController.class);
        LOGGER.info("\n获取到的bean实例：{}", bookController);
        OrderController orderController = applicationContext.getBean(OrderController.class);
        LOGGER.info("\n获取到的bean实例：{}", orderController);
    }
}
