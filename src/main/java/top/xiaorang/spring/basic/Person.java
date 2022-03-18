package top.xiaorang.spring.basic;

import org.springframework.beans.factory.InitializingBean;

public class Person implements InitializingBean {
  private String name;
  private Integer age;

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

  @Override
  public String toString() {
    return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("Person类的afterPropertiesSet初始化方法");
  }

  public void myInit() {
    System.out.println("自定义的普通方法作为初始化方法");
  }
}
