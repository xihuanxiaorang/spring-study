package top.xiaorang.spring.aop;

import org.junit.Before;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.xiaorang.spring.aop.proxy.springproxy.UserService;
import top.xiaorang.spring.aop.proxy.staticproxy.TeacherService;
import top.xiaorang.spring.aop.proxy.staticproxy.TeacherServiceImpl;

import java.lang.reflect.Proxy;

public class SpringTest {
  public static void main(String[] args) {
    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    System.out.println("-----------------开始测试--------------------");
    TeacherService teacherService = new TeacherServiceImpl();
    ClassLoader classLoader = teacherService.getClass().getClassLoader();
    Class<?>[] interfaces = teacherService.getClass().getInterfaces();
    TeacherService teacherServiceProxy =
        (TeacherService)
            Proxy.newProxyInstance(
                classLoader,
                interfaces,
                (proxy, method, arguments) -> {
                  System.out.println("---------log---------");
                  Object res = method.invoke(teacherService, arguments);
                  System.out.println("---------log---------");
                  return res;
                });
    teacherServiceProxy.teach();
  }

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

  @Test
  public void test4() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    UserService userService = ctx.getBean("userService", UserService.class);
    userService.login("小让", "123456");
  }

  @Before
  public void before() {
    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    System.out.println("-----------------开始测试--------------------");
  }
}
