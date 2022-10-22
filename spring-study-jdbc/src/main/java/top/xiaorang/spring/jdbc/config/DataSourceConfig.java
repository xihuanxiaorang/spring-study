package top.xiaorang.spring.jdbc.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author liulei
 * @description <p style = " font-weight:bold ; ">数据源配置类<p/>
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">spring-study</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/10/18 3:02
 */
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource(HikariCPProperties properties) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setJdbcUrl(properties.getJdbcUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setConnectionTimeout(properties.getConnectionTimeout());
        dataSource.setReadOnly(properties.isReadOnly());
        dataSource.setIdleTimeout(properties.getIdleTimeout());
        dataSource.setMaxLifetime(properties.getMaxLifetime());
        dataSource.setMaximumPoolSize(properties.getMaxPoolSize());
        dataSource.setMinimumIdle(properties.getMinIdle());
        return dataSource;
    }
}
