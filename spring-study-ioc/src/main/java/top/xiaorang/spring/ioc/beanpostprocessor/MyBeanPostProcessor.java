package top.xiaorang.spring.ioc.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("使用BeanPostProcessor接口中的after方法对 " + beanName + "进行增强");
    return bean;
  }
}
