package top.xiaorang.spring.annotation.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/spring-study">...</a>
 * @Copyright 博客：<a href="https://xiaorang.top">...</a>  - show me the code
 * @since 2022/8/29 2:17
 */
public class Teacher {
    /**
     * String类型
     */
    @Value("小让")
    private String name;
    /**
     * SPEL表达式
     */
    @Value("#{30-3}")
    private int age;
    @Value("#{T(Math).random()}")
    private double salary;
    /**
     * 从环境变量中取值
     */
    @Value("${teacher.workDate}")
    private String workDate;
    /**
     * 从环境变量中取值，如果没有该配置，则给一个默认值
     */
    @Value("${teacher.teach:english}")
    private String teach;

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", workDate='" + workDate + '\'' +
                ", teach='" + teach + '\'' +
                '}';
    }
}
