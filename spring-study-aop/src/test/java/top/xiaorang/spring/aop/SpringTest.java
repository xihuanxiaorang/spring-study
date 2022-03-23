package top.xiaorang.spring.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.xiaorang.spring.aop.proxy.staticproxy.TeacherService;

public class SpringTest {
  @Test
  public void test1() {
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("/applicationContext.xml");
    TeacherService teacherService =
        applicationContext.getBean("teacherServiceProxy", TeacherService.class);
    teacherService.teach();
  }
}
