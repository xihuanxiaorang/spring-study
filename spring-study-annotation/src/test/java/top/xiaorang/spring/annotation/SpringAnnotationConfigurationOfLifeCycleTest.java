package top.xiaorang.spring.annotation;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.xiaorang.spring.annotation.config.MainConfigOfLifeCycle;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">...</a>
 * @Copyright 博客：<a href="https://xiaorang.top">...</a>  - show me the code
 * @since 2022/8/27 16:32
 */
public class SpringAnnotationConfigurationOfLifeCycleTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringAnnotationConfigurationOfLifeCycleTest.class);

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            LOGGER.info(beanDefinitionName);
        }
        applicationContext.close();
    }
}
