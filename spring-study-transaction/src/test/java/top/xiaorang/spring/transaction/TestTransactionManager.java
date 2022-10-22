package top.xiaorang.spring.transaction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import top.xiaorang.spring.transaction.config.MainConfig;

/**
 * @author liulei
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">spring-study</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/10/18 2:51
 */
@ContextConfiguration(classes = {MainConfig.class})
@ExtendWith(SpringExtension.class)
public class TestTransactionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestTransactionManager.class);

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Test
    public void testTransactionIsolation() {
        LOGGER.info("当前事务管理器：{}", dataSourceTransactionManager);
    }
}

