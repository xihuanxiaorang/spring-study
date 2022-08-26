package top.xiaorang.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">...</a>
 * @Copyright 博客：<a href="https://xiaorang.top">...</a>  - show me the code
 * @since 2022/8/26 22:00
 */
public class XmlAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlAspect.class);

    public void before(JoinPoint joinPoint) {
        LOGGER.info("before通知：{}", joinPoint);
    }

    public void after(JoinPoint joinPoint) {
        LOGGER.info("after通知：{}", joinPoint);
    }

    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            LOGGER.info("around-before通知：{}", joinPoint);
            Object result = joinPoint.proceed();
            LOGGER.info("around-afterReturning通知：{}", joinPoint);
            return result;
        } catch (Throwable e) {
            LOGGER.info("around-afterThrowing通知：{} with exception：{}", joinPoint, e.getMessage());
            throw new RuntimeException(e);
        } finally {
            LOGGER.info("around-after通知：{}", joinPoint);
        }
    }

    public void afterReturning(JoinPoint joinPoint, Object result) {
        LOGGER.info("afterReturning通知：{}，返回值：{}", joinPoint, result);
    }

    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        LOGGER.info("afterThrowing通知：{} with exception：{}", joinPoint, e.getMessage());
    }
}
