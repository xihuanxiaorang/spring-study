package top.xiaorang.spring.jdbc.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author liulei
 * @description <p style = " font-weight:bold ; ">HikariCP连接池配置<p/>
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">spring-study</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/10/18 3:50
 */
@Component
@PropertySource(value = {"classpath:db.properties"})
@Data
public class HikariCPProperties {
    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.connectionTimeout}")
    private long connectionTimeout;
    @Value("${jdbc.readOnly}")
    private boolean isReadOnly;
    @Value("${jdbc.pool.idleTimeout}")
    private long idleTimeout;
    @Value("${jdbc.pool.maxLifetime}")
    private long maxLifetime;
    @Value("${jdbc.pool.maxPoolSize}")
    private int maxPoolSize;
    @Value("${jdbc.pool.minIdle}")
    private int minIdle;
}
