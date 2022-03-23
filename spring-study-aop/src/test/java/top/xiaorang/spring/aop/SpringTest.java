package top.xiaorang.spring.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.xiaorang.spring.aop.proxy.staticproxy.TeacherService;

import java.lang.reflect.Proxy;

public class SpringTest {
  @Test
  public void test1() {
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("/applicationContext.xml");
    TeacherService teacherService =
        applicationContext.getBean("teacherServiceProxy", TeacherService.class);
    teacherService.teach();
  }

  @Test
  public void test2() {
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("/applicationContext.xml");
    TeacherService teacherService =
        applicationContext.getBean("teacherService", TeacherService.class);
    ClassLoader classLoader = teacherService.getClass().getClassLoader();
    Class<?>[] interfaces = teacherService.getClass().getInterfaces();
    TeacherService teacherServiceProxy =
        (TeacherService)
            Proxy.newProxyInstance(
                classLoader,
                interfaces,
                (proxy, method, args) -> {
                  System.out.println("---------log---------");
                  Object res = method.invoke(teacherService, args);
                  System.out.println("---------log---------");
                  return res;
                });
    teacherServiceProxy.teach();
  }
}
