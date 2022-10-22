package top.xiaorang.spring.aop;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.xiaorang.spring.aop.config.AopConfig;
import top.xiaorang.spring.aop.service.MemberService;

public class SpringTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringTest.class);

    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        MemberService memberService = ctx.getBean("memberService", MemberService.class);
        LOGGER.info("========================这是一条华丽的分割线========================");
        memberService.get();
        LOGGER.info("========================这是一条华丽的分割线========================");
        try {
            memberService.delete(1L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test1() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AopConfig.class);
        MemberService memberService = ctx.getBean("memberService", MemberService.class);
        LOGGER.info("========================这是一条华丽的分割线========================");
        memberService.get();
        LOGGER.info("========================这是一条华丽的分割线========================");
        try {
            memberService.delete(1L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
