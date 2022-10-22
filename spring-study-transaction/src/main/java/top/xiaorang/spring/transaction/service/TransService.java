package top.xiaorang.spring.transaction.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author liulei
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">转账业务spring-study</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/10/21 20:43
 */
@Service
public class TransService {
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public TransService(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }


    public void transfer() {
        transactionTemplate.executeWithoutResult((transactionStatus) -> {
            jdbcTemplate.update("update account set balance = balance + 100 where name = 'javaboy'");
            int i = 1 / 0;
            jdbcTemplate.update("update account set balance = balance - 100 where name = 'itboyhub'");
        });
    }
}
