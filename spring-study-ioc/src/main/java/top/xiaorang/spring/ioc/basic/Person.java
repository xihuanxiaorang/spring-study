package top.xiaorang.spring.ioc.basic;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;

public class Person implements InitializingBean, DisposableBean {
  private String name;
  private Integer age;
  private Date birthday;

  private Person() {
    System.out.println("Person类私有构造方法");
  }

  public Person(String name, Integer age) {
    System.out.println("Person类全参构造方法");
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    System.out.println("name=" + name);
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    System.out.println("age=" + age);
    this.age = age;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    System.out.println("birthday=" + birthday);
    this.birthday = birthday;
  }

  @Override
  public String toString() {
    return "Person{" + "name='" + name + '\'' + ", age=" + age + ", birthday=" + birthday + '}';
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("Person类的afterPropertiesSet初始化方法");
  }

  public void myInit() {
    System.out.println("自定义的普通方法作为初始化方法");
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("Person类的destroy销毁方法");
  }

  public void myDestroy() {
    System.out.println("自定义的普通方法作为销毁方法");
  }
}
