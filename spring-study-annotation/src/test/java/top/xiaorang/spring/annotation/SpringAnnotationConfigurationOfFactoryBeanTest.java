package top.xiaorang.spring.annotation;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.xiaorang.spring.annotation.config.MainConfig2;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">...</a>
 * @Copyright 博客：<a href="https://xiaorang.top">...</a>  - show me the code
 * @since 2022/8/27 16:32
 */
public class SpringAnnotationConfigurationOfFactoryBeanTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringAnnotationConfigurationOfFactoryBeanTest.class);

    @Test
    public void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            LOGGER.info(beanDefinitionName);
        }

        Object bean = applicationContext.getBean("colorFactoryBean");
        Object bean2 = applicationContext.getBean("colorFactoryBean");
        LOGGER.info("获取到的bean实例：{}", bean);
        LOGGER.info("获取到的实例是否同一个？{}", bean == bean2);

        Object bean3 = applicationContext.getBean("&colorFactoryBean");
        LOGGER.info("获取到的bean实例：{}", bean3);
    }
}
