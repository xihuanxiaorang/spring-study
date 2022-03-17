package top.xiaorang.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.xiaorang.spring.basic.Person;

public class SpringTest {

  @Test
  public void test1() {
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("/applicationContext.xml");
    Person person = applicationContext.getBean("person", Person.class);
    System.out.println(person);
  }
}
