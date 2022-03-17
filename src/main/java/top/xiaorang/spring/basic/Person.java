package top.xiaorang.spring.basic;

public class Person {
  private String name;
  private Integer age;

  private Person() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
