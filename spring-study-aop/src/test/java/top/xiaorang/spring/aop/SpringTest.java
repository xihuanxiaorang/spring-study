package top.xiaorang.spring.aop;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
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

  @Test
  public void test3() {
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("/applicationContext.xml");
    TeacherService teacherService =
        applicationContext.getBean("teacherService", TeacherService.class);
    Class<?>[] interfaces = teacherService.getClass().getInterfaces();
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(teacherService.getClass());
    enhancer.setInterfaces(interfaces);
    enhancer.setCallback(
        (MethodInterceptor)
            (object, method, args, methodProxy) -> {
              System.out.println("---------Cglib log---------");
              Object res = method.invoke(teacherService, args);
              System.out.println("---------Cglib log---------");
              return res;
            });
    TeacherService teacherServiceProxy = (TeacherService) enhancer.create();
    teacherServiceProxy.teach();
  }
}
