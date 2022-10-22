package top.xiaorang.spring.jdbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import top.xiaorang.spring.jdbc.config.MainConfig;

import javax.sql.DataSource;

/**
 * @author liulei
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">spring-study</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/10/18 4:08
 */
@ContextConfiguration(classes = {MainConfig.class})
@ExtendWith(SpringExtension.class)
public class TestDataSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDataSource.class);

    @Autowired
    private DataSource dataSource;

    @Test
    public void test() {
        LOGGER.info("当前数据源：{}", dataSource);
    }
}
