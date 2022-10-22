package top.xiaorang.spring.transaction.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liulei
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">spring-study</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/10/22 17:41
 */
@Service
public class AccountService2 {
    private final JdbcTemplate jdbcTemplate;
    private final AccountService accountService;

    public AccountService2(JdbcTemplate jdbcTemplate, AccountService accountService) {
        this.jdbcTemplate = jdbcTemplate;
        this.accountService = accountService;
    }

    @Transactional
    public void handle2() {
        jdbcTemplate.update("update account set balance = ? where name = ?", 1, "javaboy");
        accountService.handle1();
    }
}
