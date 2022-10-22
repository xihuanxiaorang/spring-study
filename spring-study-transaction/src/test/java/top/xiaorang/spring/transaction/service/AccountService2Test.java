package top.xiaorang.spring.transaction.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import top.xiaorang.spring.transaction.config.MainConfig;

/**
 * @author liulei
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">spring-study</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/10/22 17:56
 */
@ContextConfiguration(classes = {MainConfig.class})
@ExtendWith(SpringExtension.class)
class AccountService2Test {
    @Autowired
    private AccountService2 accountService2;

    @Test
    void handle2() {
        accountService2.handle2();
    }
}