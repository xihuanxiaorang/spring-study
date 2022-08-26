package top.xiaorang.spring.aop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.xiaorang.spring.aop.entity.Member;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">...</a>
 * @Copyright 博客：<a href="https://xiaorang.top">...</a>  - show me the code
 * @since 2022/8/26 21:53
 */
@Service
public class MemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberService.class);

    public Member get(Long id) {
        LOGGER.info("getMemberById Method...");
        return new Member();
    }

    public Member get() {
        LOGGER.info("getMember Method...");
        return new Member();
    }

    public void save(Member member) {
        LOGGER.info("save Member Method...");
    }

    public boolean delete(Long id) throws Exception {
        LOGGER.info("delete Method...");
        throw new Exception("spring aop ThrowAdvice 演示");
    }
}
