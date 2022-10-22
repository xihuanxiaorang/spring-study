package top.xiaorang.spring.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.xiaorang.spring.ioc.basic.Customer;
import top.xiaorang.spring.ioc.basic.Person;
import top.xiaorang.spring.ioc.basic.UserService;

import java.sql.Connection;

public class SpringTest {

    @Test
    public void test1() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("/applicationContext.xml");
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
    }

    @Test
    public void test2() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Person person = ctx.getBean("person", Person.class);
        System.out.println(person);
        Person person1 = (Person) ctx.getBean("person");
        System.out.println(person1);
        Person person2 = ctx.getBean(Person.class);
        System.out.println(person2);
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        System.out.println(ctx.containsBean("person"));
        System.out.println(ctx.containsBeanDefinition("person"));
    }

    @Test
    public void test3() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Customer customer = ctx.getBean("customer", Customer.class);
        System.out.println(customer);
    }

    @Test
    public void test4() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        UserService userService = ctx.getBean("userService", UserService.class);
        userService.saveAllUsers();
    }

    @Test
    public void test5() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Connection connection = ctx.getBean("connection", Connection.class);
        System.out.println(connection);
        Connection connection1 = ctx.getBean("connection", Connection.class);
        System.out.println(connection1);
    }

    @Test
    public void test6() {
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("/applicationContext.xml");
        Person person = ctx.getBean("person", Person.class);
        System.out.println(person);
        ctx.close();
    }
}
