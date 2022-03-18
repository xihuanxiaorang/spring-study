package top.xiaorang.spring.ioc.basic;

import java.io.Serializable;
import java.util.*;

public class Customer implements Serializable {
  private String name;
  private Integer age;
  private String[] emails;
  private Set<String> tels;
  private List<String> addresses;
  private Map<String, String> qqs;
  private Properties p;

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

  public String[] getEmails() {
    return emails;
  }

  public void setEmails(String[] emails) {
    this.emails = emails;
  }

  public Set<String> getTels() {
    return tels;
  }

  public void setTels(Set<String> tels) {
    this.tels = tels;
  }

  public List<String> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<String> addresses) {
    this.addresses = addresses;
  }

  public Map<String, String> getQqs() {
    return qqs;
  }

  public void setQqs(Map<String, String> qqs) {
    this.qqs = qqs;
  }

  public Properties getP() {
    return p;
  }

  public void setP(Properties p) {
    this.p = p;
  }

  @Override
  public String toString() {
    return "Customer{"
        + "name='"
        + name
        + '\''
        + ", age="
        + age
        + ", emails="
        + Arrays.toString(emails)
        + ", tels="
        + tels
        + ", addresses="
        + addresses
        + ", qqs="
        + qqs
        + ", p="
        + p
        + '}';
  }
}
