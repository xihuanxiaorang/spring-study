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
}
