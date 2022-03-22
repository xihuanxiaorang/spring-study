# 第一个Spring程序
## 1、环境搭建
开发环境：
```markdown
1. JDK1.8+
2. Maven 3.5+
3. SpringFramework 5.1.4
```
依赖的jar包：
```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.3.16</version>
    </dependency>
</dependencies>
```
Spring的配置文件：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647503154790-87cd2a81-63a5-4481-9838-4c5c12e19a76.png#clientId=u58c72a4d-3a53-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=991&id=u244636ad&originHeight=991&originWidth=1337&originalType=binary&ratio=1&rotation=0&showTitle=false&size=306006&status=done&style=none&taskId=u5d8880ce-b0c6-4733-9ea1-6a2fe56a33f&title=&width=1337)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

</beans>
```
## 2、Spring核心API
ApplicationContext：是一个接口，主要用于对象的创建。其实现类主要有`ClassPathXmlApplicationContext`和`XmlWebApplicationContext`。ApplicationContext工厂对象占用大量内存，一个应用中只会有一个工厂对象(单例)，并且是线程安全的。
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647504014150-0d252feb-0ff6-452b-9801-a44ba011737e.png#clientId=u58c72a4d-3a53-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=603&id=u3f9a8dc0&originHeight=603&originWidth=645&originalType=binary&ratio=1&rotation=0&showTitle=false&size=211381&status=done&style=none&taskId=ue628a453-4df0-40bc-8596-34dfabe7bf4&title=&width=645)
## 3、程序开发
```markdown
1. 创建需要Spring进行管理的类
2. 配置配置文件 <bean id="person" class="xxx.basic.person" /> 
3. 编写测试类，从工厂中获取对象
   ApplicationContext
       |- ClassPathXmlApplicationContext
   ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
   Person person = ctx.getBean("person", Person.class);
```
### 1、编写Person类
```java
public class Person {
  private String name;
  private Integer age;

  private Person() {
    System.out.println("Person类构造方法");
  }

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
```
### 2、编写配置文件
```xml
<bean id="person" class="top.xiaorang.spring.basic.Person" />
```
### 3、编写测试方法
```java
@Test
public void test1() {
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("/applicationContext.xml");
    Person person = applicationContext.getBean("person", Person.class);
    System.out.println(person);
}
```
📝可以看到构造方法被调用。
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647519312972-18ebb9e5-e2ac-4321-bb2b-3f18bd8137c8.png#clientId=u58c72a4d-3a53-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=300&id=u35ad2820&originHeight=300&originWidth=1464&originalType=binary&ratio=1&rotation=0&showTitle=false&size=136315&status=done&style=none&taskId=ub85377da-79d8-4c3d-9f8c-84221b02285&title=&width=1464)
💡注意
当没有在`applicationContext.xml`文件中注册`Person`类时，从工厂中获取该类对象会报如下错误。
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647504744157-74281113-e1a3-4f5f-97f1-19389a831a5a.png#clientId=u58c72a4d-3a53-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=273&id=u34998575&originHeight=273&originWidth=1409&originalType=binary&ratio=1&rotation=0&showTitle=false&size=208513&status=done&style=none&taskId=u9c1f9e52-afa4-4ea0-9148-892c37aff88&title=&width=1409)
## 4、细节分析

- spring工厂创建的对象叫做bean或者组件(component)
- spring工厂的相关方法
```java
Person person = applicationContext.getBean("person", Person.class);
System.out.println(person);
// 没有指定类型，返回值需要进行强转
Person person1 = (Person) applicationContext.getBean("person");
System.out.println(person1);
// 使用该方法时，在spring配置文件中只能有一个class是Person的bean，否则会报错
Person person2 = applicationContext.getBean(Person.class);
System.out.println(person2);
// 获取spring配置文件中所有bean表标签的id
String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
for (String beanDefinitionName : beanDefinitionNames) {
  System.out.println(beanDefinitionName);
}
// 用于判断是否存在指定id或者name值的bean
System.out.println(ctx.containsBean("person"));
// 用于判断是否存在指定id值的bean
System.out.println(ctx.containsBeanDefinition("person"));
```
💡注意

1. 当使用`applicationContext.getBean(xxx.class)`方法获取对象的时候，如果spring配置中有两个bean的class都是xxx，则会报如下错误。

![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647506443595-10466624-9b7e-4a02-b5cd-efe5589c9749.png#clientId=u58c72a4d-3a53-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=423&id=u512870c7&originHeight=423&originWidth=1841&originalType=binary&ratio=1&rotation=0&showTitle=false&size=384537&status=done&style=none&taskId=u108a573f-dea3-4992-9434-ba59b43afa9&title=&width=1841)

2. 当在spring配置文件中注册bean时，bean标签里只有class，没有id值，那么它默认的id值是`全限定类名#0`。如`<bean class="top.xiaorang.basic.Person" />`，那么id值为`top.xiaorang.basic.Person#0`。如果这个bean只需要使用一次，那么可以省略id值，如果在其他bean中会引用到这个bean，这个时候就需要设置id值。
2. bean标签中的name属性，为bean对象定义别名，在获取对象的时候也可以通过别名来获取。

区别(与id属性)：

   - 别名可以定义多个(用逗号分隔)，但是id属性只能有一个值
   - id属性值，命名要求：必须以字母开头，可以使用字母，数字，下划线，连字符等符号，不可以以特殊字符开头/person，虽然发展到现在id就像name一样没有了命名的限制。
4. 获取出来的对象是单例的。如下图，三种获取person对象的方式打印出来的对象引用都是一样的，说明获取到的对象都是同一个。

![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647508323924-522999bc-41d9-4271-8cb0-a28488f8cd9e.png#clientId=u58c72a4d-3a53-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=818&id=u2e1725aa&originHeight=818&originWidth=1476&originalType=binary&ratio=1&rotation=0&showTitle=false&size=524514&status=done&style=none&taskId=u740e33f6-4161-4f86-b7a3-02402693a1e&title=&width=1476)
## 5、代码分析

1. `ClassPathXmlApplicationContext`工厂读取`applicationContext.xml`配置文件。
1. 获得bean标签的相关信息，id值为"person"，class值为"top.xiaorang.spring.basic.Person"，通过反射去调用Person类的构造方法创建对象。即使构造方法是私有的，也可以创建出来对象。
```java
Class clazz = Class.forName(class的值);
id的值 = Clazz.newInstance();
```
# 整合日志框架
## 1、为什么要整合日志框架？🤔
`spring`与日志框架进行整合，利用日志框架可以输出spring框架在运行过程中的一些重要信息，便于了解`spring`框架的运行过程。
## 2、如何整合
引入依赖
```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>1.7.25</version>
</dependency>
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```
编写配置文件
```properties
# resources文件夹根目录下
### 配置根
log4j.rootLogger=debug,console
### 日志输出到控制台显示
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.Target=System.out
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
```
## 3、测试
```java
@Test
public void test1() {
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("/applicationContext.xml");
    Person person = applicationContext.getBean("person", Person.class);
    System.out.println(person);
}
```
观察控制台，发现已经打印`spring`运行过程中的相关日志。
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647517901947-e0ada678-b5ca-4c73-aba5-c5fa3a4f3b58.png#clientId=u58c72a4d-3a53-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=269&id=u844a5163&originHeight=269&originWidth=1461&originalType=binary&ratio=1&rotation=0&showTitle=false&size=167845&status=done&style=none&taskId=uefdb9785-826d-4113-af28-888633082d5&title=&width=1461)
# 依赖注入(Dependcy Injection)
## 1、什么是依赖注入
通过`spring`工厂以及配置文件为所创建的对象的成员变量赋值。
## 2、为什么需要依赖注入
通过编码的方式给成员变量赋值，存在耦合，以后维护代码需要修改源码，如果使用依赖注入的方式，代码不需要修改，只需要修改配置文件中的值即可，这样的话可以做到解耦合。
## 3、程序开发
### 1、编写Person类
类还是以前的`Person`类，里面有两个属性`name`和`age`，书写`getter``setter`方法
```java
public class Person {
  private String name;
  private Integer age;

  private Person() {
    System.out.println("Person类构造方法");
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
}
```
### 2、编写配置文件
在配置文件声明`Person`类的`bean`标签中使用`property`标签给类中的成员变量进行赋值。
```xml
<bean id="person" class="top.xiaorang.spring.basic.Person">
    <property name="name" value="小让"/>
    <property name="age" value="10"/>
</bean>
```
### 3、编写测试类
```java
@Test
public void test1() {
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("/applicationContext.xml");
    Person person = applicationContext.getBean("person", Person.class);
    System.out.println(person);
}
```
发现打印出来的对象中，`name`和`age`属性已经被赋值。
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647519589628-e4780f34-c9d0-4dd1-8353-5edcda3fba5e.png#clientId=u58c72a4d-3a53-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=332&id=u43177a8b&originHeight=332&originWidth=1425&originalType=binary&ratio=1&rotation=0&showTitle=false&size=193410&status=done&style=none&taskId=u0ee41d52-1b3a-4a3f-9410-de73eb8039f&title=&width=1425)
## 4、代码分析
通过上面的代码以及输出结果，可以看出`spring`底层通过反射调用对象的构造方法创建出对象，之后调用对象的`setter`方法给成员变量赋值，这种赋值方式称之为set注入。
# set注入详解
针对不同类型的成员变量，在`property`标签中需要嵌套其他标签才能给成员变量赋值。
![](https://cdn.nlark.com/yuque/0/2021/png/22153570/1634190396828-ca5b00e7-3a51-4092-b9d9-10445d747b50.png?x-oss-process=image%2Fresize%2Cw_750%2Climit_0#crop=0&crop=0&crop=1&crop=1&from=url&id=OYErP&margin=%5Bobject%20Object%5D&originHeight=348&originWidth=750&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=none&title=)
## 1、JDK内置类型
常见的成员变量类型有①8种基本类型+String②数组类型③set集合④list集合⑤Map集合⑥Properties集合
### 1、8种基本类型+String
`property`标签嵌套`value`标签。
```xml
<property>
  <value></value>
</property>
```
### 2、数组
`property`标签嵌套`array`标签。
```xml
<property>
  <array>
    <value></value>
    <value></value>
  </array>
</property>
```
### 3、set集合
`property`标签嵌套`set`标签。
```xml
<property>
  <set>
    <value></value>
    <value></value>
  </set>
</property>
```
### 4、list集合
`property`标签嵌套`list`标签。
```xml
<property>
  <list>
    <value></value>
    <value></value>
  </list>
</property>
```
### 5、Map集合
`property`标签嵌套`map`标签，`map`标签再嵌套`entry`标签。其中，key有特定的`key`标签，值根据对应类型选择标签。
```xml
<property>
  <map>
    <entry>
      <key>
          <value></value>
      </key>
      <value></value>
    </entry>
    <entry key="" value=""/>
  </map>
</property>
```
### 6、Propties集合
`property`标签嵌套`props`标签，`props`标签嵌套`prop`标签。
```xml
<property>
    <props>
        <prop key=""></prop>
        <prop key=""></prop>
    </props>
</property>
```
### 7、复杂的JDK类型(Date)
spring提供的默认的日期类型格式为2022/03/17，如果你的日期格式为2022-03-17的话，spring不支持，则需要自定义类型转换器。后续在将自定义类型转换器的时候会详细解释。
### 8、测试
#### 1、编写Customer类
```java
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
    return "Customer{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", emails=" + Arrays.toString(emails) +
            ", tels=" + tels +
            ", addresses=" + addresses +
            ", qqs=" + qqs +
            ", p=" + p +
            '}';
  }
}
```
#### 2、编写配置文件，给成员变量赋值
```xml
<bean id="customer" class="top.xiaorang.spring.basic.Customer">
  <property name="name" value="小明"/>
  <property name="age" value="18"/>
  <property name="emails">
      <array>
          <value>15019474951@163.com</value>
          <value>623052545@qq.com</value>
          <value>2329862718@qq.com</value>
      </array>
  </property>
  <property name="tels">
      <set>
          <value>15019474951</value>
          <value>13838384388</value>
          <value>18274831189</value>
      </set>
  </property>
  <property name="addresses">
      <list>
          <value>珠光村东区91栋413</value>
          <value>龙胜一小队xxx栋605</value>
      </list>
  </property>
  <property name="qqs">
      <map>
          <entry>
              <key>
                  <value>xiaorang</value>
              </key>
              <value>2329862718</value>
          </entry>
          <entry key="sanshi" value="623052545"/>
      </map>
  </property>
  <property name="p">
      <props>
          <prop key="birthday">1995-07-13</prop>
          <prop key="high">168</prop>
      </props>
  </property>
</bean>
```
#### 3、测试
```java
@Test
public void test3() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
    Customer customer = ctx.getBean("customer", Customer.class);
    System.out.println(customer);
}
```
可以看到所有成员变量均被赋值。
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647522424423-b3b6d480-df0b-45a0-839c-70a1d0e5d50d.png#clientId=u58c72a4d-3a53-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=462&id=u082cda85&originHeight=462&originWidth=1425&originalType=binary&ratio=1&rotation=0&showTitle=false&size=306214&status=done&style=none&taskId=uc130d03e-5281-46dc-bbe6-da61443533a&title=&width=1425)
## 2、自定义类型(用户创建的类)
当类中的成员变量是自定义类型的时候(即程序员自己创建的类)，给成员变量赋值可以使用`property`嵌套`bean`标签。
```xml
<bean id="userService" class="top.xiaorang.spring.basic.UserServiceImpl">
    <property name="userDao">
        <bean class="top.xiaorang.spring.basic.UserDaoImpl"/>
    </property>
</bean>
```
上面这种注入方式虽然运行的结果没有问题，但实际上被注入的UserDao对象多次创建，造成资源的浪费。
正确的做法是使用`bean`标签创建一个`UserDao`对象，然后在给`UserService`对象中的`userDao`成员变量赋值时应该去引用已经创建的`UserDao`对象。
```xml
<bean id="userDao" class="top.xiaorang.spring.basic.UserDaoImpl"/>

<bean id="userService" class="top.xiaorang.spring.basic.UserServiceImpl">
    <property name="userDao">
        <ref bean="userDao" />
    </property>
</bean>
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647525377450-03bf4505-bd53-4dc1-9135-4093d056bccf.png#clientId=u58c72a4d-3a53-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=84&id=u495dfcde&originHeight=84&originWidth=1424&originalType=binary&ratio=1&rotation=0&showTitle=false&size=57544&status=done&style=none&taskId=u6331d3bb-2def-4a05-9b45-79c05a82732&title=&width=1424)
## 3、set注入的简化写法
### 1、基于属性简化
```xml
<property name="">
  <value></value>
</property>
<!- 注意,value属性只能简化8种基本类型+String,其实细心的童鞋已经发现上面我已经用了简化的方式😁--->
<property name="" value=""/>

<property name="">
  <ref bean="" />
</property>
<!---------->
<property name="" ref=""/>
```
### 2、基于p命名空间简化
```xml
<bean id="" class="">
  <property>
    <value></value>
  </property>
</bean>
<!--只能简化8种基本类型+String--->
<bean id="" class="" p:xxx=""/>

<bean id="" class="">
  <property name="">
    <ref bean="" />
  </property>
</bean>
<!--------------------->
<bean id="" class="" p:xxx-ref=""/>
```
# 构造注入
```markdown
- 注入：通过spring配置给对象的成员变量赋值
- set注入：sprin创建完对象后，通过配置文件使用set方法给对象的成员变量赋值
- 构造注入：spring通过构造方法创建对象的同时给成员变量赋值
```
## 1、开发步骤
### 1、编写Person类，提供有参构造
```java
public class Person {
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
}
```
### 2、编写配置文件
```xml
<bean id="person" class="top.xiaorang.spring.basic.Person">
    <constructor-arg name="name" value="小让"/>
    <constructor-arg name="age" value="10"/>
</bean>
```
### 3、测试
```java
@Test
public void test1() {
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("/applicationContext.xml");
    Person person = applicationContext.getBean("person", Person.class);
    System.out.println(person);
}
```
发现调用的Person类的全参构造方法，没有调用无参构造，并且成员变量`name`和`age`已被赋值。
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647526531683-dca1704f-e3ec-43d7-99c6-605b20bd69b2.png#clientId=u58c72a4d-3a53-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=416&id=uad4e68fc&originHeight=416&originWidth=1433&originalType=binary&ratio=1&rotation=0&showTitle=false&size=269391&status=done&style=none&taskId=u255436a8-52d8-4630-b001-08e0e71f644&title=&width=1433)
## 2、构造方法重载
### 1、参数个数不同时
通过嵌套的`constructor-arg`标签数量即可区分。
### 2、构造参数个数相同时
通过`constructor-arg`标签中的`name`属性进行区分。
## 3、总结
![](https://cdn.nlark.com/yuque/0/2021/png/22153570/1634190411921-15d4860d-3257-4bfe-85b0-7fb4c0d48f0f.png?x-oss-process=image%2Fresize%2Cw_750%2Climit_0#crop=0&crop=0&crop=1&crop=1&from=url&id=Upocz&margin=%5Bobject%20Object%5D&originHeight=353&originWidth=750&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=none&title=)
# 控制反转与依赖注入
## 1、IOC(控制反转)
把对对象创建的控制权交给spring管理。
## 2、DI(依赖注入)
依赖注入：当一个类需要另一个类的时候，就意味着依赖，一旦出现依赖，就可以把另一个类作为本类的成员变量，最终通过`spring`配置文件进行注入(即对成员变量赋值)，这样可以做到解耦合。
# 创建复杂对象
## 1、什么叫简单对象，复杂对象？
![](https://cdn.nlark.com/yuque/0/2021/png/22153570/1634190444647-d325e165-57b1-4070-aecd-c6583c18f27c.png?x-oss-process=image%2Fresize%2Cw_750%2Climit_0#crop=0&crop=0&crop=1&crop=1&from=url&id=aZw7i&margin=%5Bobject%20Object%5D&originHeight=319&originWidth=750&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=none&title=)
## 2、Spring工厂创建复杂对象的3种方式
### 1、实现FactoryBean接口
#### 1、编写ConnectionFactoryBean类，实现FactoryBean接口
```java
public class ConnectionFactoryBean implements FactoryBean<Connection> {
  private String driverClassName;
  private String url;
  private String username;
  private String password;

  @Override
  public Connection getObject() throws Exception {
    Class.forName(driverClassName);
    return DriverManager.getConnection(url, username, password);
  }

  @Override
  public Class<?> getObjectType() {
    return Connection.class;
  }

  @Override
  public boolean isSingleton() {
    return false;
  }

  public String getDriverClassName() {
    return driverClassName;
  }

  public void setDriverClassName(String driverClassName) {
    this.driverClassName = driverClassName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
```
#### 2、编写配置文件
```xml
<bean id="connection" class="top.xiaorang.spring.factorybean.ConnectionFactoryBean">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
    <property name="url"
              value="jdbc:mysql://localhost:3306/atguigudb?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false&amp;serverTimezone=Asia/Shanghai"/>
    <property name="username" value="root"/>
    <property name="password" value=""/>
</bean>
```
#### 3、测试
```java
@Test
public void test5() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
    Connection connection = ctx.getBean("connection", Connection.class);
    System.out.println(connection);
    Connection connection1 = ctx.getBean("connection", Connection.class);
    System.out.println(connection1);
}
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647528486471-b3173d3e-57cf-4956-a353-31dcc104c4db.png#clientId=u58c72a4d-3a53-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=412&id=u1782b03f&originHeight=412&originWidth=1457&originalType=binary&ratio=1&rotation=0&showTitle=false&size=293325&status=done&style=none&taskId=u3c463135-c140-4ef3-9037-a5c8f2207ac&title=&width=1457)
#### 4、代码分析

1. 在使用`getBean`方法获取简单对象时，获取到的都是`bean`标签中`class`属性对应的类的对象；在使用`getBean`获取复杂对象(实现了`FactoryBean`接口)时，获取到的是`bean`标签中`class`属性对应的类的对象中`getObject`方法返回的对象。
1. 其中的`isSingleton`方法返回true表示创建的是一个单例对象，只会创建一次；如果返回的是false，则每一次都会创建一个新的对象。此处的数据库连接对象(connection)则返回false，在使用的时候都应该每次获取一个新的对象。
1. 现在使用`ctx.getBean("connection")`获取到的`getObject`方法中返回的对象，如果就是想获取`FactoryBean`接口的实现类呢？那么需要使用`ctx.getBean("&connection")`，获取到的就是`ConnectionFactoryBean`对象。
#### 5、总结
`spring`获取到`ConnectionFactoryBean`对象，通过`instanceof`判断是`FactoryBean`接口的实现类，进而返回`getObject`方法中返回的对象。
使用该方式创建复杂对象，是`spring`原生提供的，后续在`spring`整合其他框架时，会大量的应用到`FactoryBean`。
#### 2、实例工厂的方式
##### 1、编写ConnectionFactory类
```java
public class ConnectionFactory {
  private String driverClassName;
  private String url;
  private String username;
  private String password;

  public Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName(driverClassName);
      connection = DriverManager.getConnection(url, username, password);
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public String getDriverClassName() {
    return driverClassName;
  }

  public void setDriverClassName(String driverClassName) {
    this.driverClassName = driverClassName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
```
##### 2、编写配置文件
```xml
<bean id="connectionFactory" class="top.xiaorang.spring.factory.ConnectionFactory">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
    <property name="url"
              value="jdbc:mysql://localhost:3306/atguigudb?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false&amp;serverTimezone=Asia/Shanghai"/>
    <property name="username" value="root"/>
    <property name="password" value=""/>
</bean>
<bean id="connection" factory-bean="connectionFactory" factory-method="getConnection"/>
```
##### 3、测试
```java
@Test
public void test5() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
    Connection connection = ctx.getBean("connection", Connection.class);
    System.out.println(connection);
    Connection connection1 = ctx.getBean("connection", Connection.class);
    System.out.println(connection1);
}
```
可以看到获取出来的`connection`是同一个，所以该方式创建出来的对象是单例的。并且创建了`ConnectioFactory`单例对象。
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647529940928-39a272b6-2748-4b82-9e86-203d67cd7e21.png#clientId=u58c72a4d-3a53-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=490&id=ue4b77cbd&originHeight=490&originWidth=1463&originalType=binary&ratio=1&rotation=0&showTitle=false&size=349320&status=done&style=none&taskId=u06022d9a-11d3-4a56-bbe3-6ba8c048dbe&title=&width=1463)
#### 3、静态工厂的方式
##### 1、编写StaticConnectionFactory类
```java
public class StaticConnectionFactory {
  private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
  private static final String URL =
      "jdbc:mysql://localhost:3306/atguigudb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "";

  private StaticConnectionFactory() {}

  public static Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName(DRIVER_CLASS_NAME);
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return connection;
  }
}
```
##### 2、编写配置文件
```xml
<bean id="connection" class="top.xiaorang.spring.staticfactory.StaticConnectionFactory"
          factory-method="getConnection"/>
```
##### 3、测试
```java
@Test
public void test5() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
    Connection connection = ctx.getBean("connection", Connection.class);
    System.out.println(connection);
    Connection connection1 = ctx.getBean("connection", Connection.class);
    System.out.println(connection1);
}
```
可以看到获取出来的`connection`是同一个，所以该方式创建出来的对象是单例的。
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647585379409-b362da3c-6815-4dd9-8e09-b8bbf364dea6.png#clientId=u4c720250-2228-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=425&id=u8577ea43&originHeight=425&originWidth=1462&originalType=binary&ratio=1&rotation=0&showTitle=false&size=299758&status=done&style=none&taskId=u0803bb9c-de44-4d86-9043-81e31c7e81b&title=&width=1462)
## 3、总结
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647586313017-4f0876ec-aa40-41ac-8b5e-490aaa2c2007.png#clientId=u4c720250-2228-4&crop=0&crop=0&crop=1&crop=1&from=paste&id=u75db6b87&originHeight=369&originWidth=1788&originalType=url&ratio=1&rotation=0&showTitle=false&size=180255&status=done&style=none&taskId=ufa8f8617-ef80-4a0e-a443-69f06c765d8&title=)
# 控制spring工厂创建对象的次数
## 1、为什么要控制创建对象的次数？
因为有的对象开销比较大，创建多个容易造成内存资源的浪费。
## 2、什么样的对象只创建一次，什么样的对象需要每次创建新的？
创建一次的对象，如`SqlSessionFactroy``Service``Dao`。
创建多次的对象，如`Connection``SqlSession`，因为每次控制事务时不一样，所以需要新的。
总结：可以共用，并且线程安全的，就可以只需要创建一次，反之则需要创建新的。
## 3、那么如何控制呢？
对简单对象来说，声明`bean`标签时默认是单例模式，即只会创建一次对象。如果想创建多次，则让其中的`scope`属性等于`prototype`即可。
对复杂对象来说，如果实现了`FactoryBean`接口，在重写`isSingleton`方法时，如果返回`true`，则只会创建一次对象，如果返回`false`，则每次都会创建新的对象。如果没有实现`FactoryBean`接口，而是用的实例工厂和静态工厂方式来创建对象，则还是通过`bean`标签中的`scope`属性来控制。
# 对象的生命周期
## 1、什么是对象的生命周期？
指的是一个对象从创建、初始化到销毁的一个完整过程。
## 2、生命周期的三个阶段
### 1、创建阶段(Spring工厂何时创建对象)
结论：
当创建的是单例对象时，并且`bean`标签中的`lazy-init`属性为false(默认为false)时，在spring工厂创建的时候就会创建出对象并给对象中的成员变量进行赋值。但如果`bean`标签中的`lazy-init`属性为true，则会推迟到获取对象时才会去创建对象。
当创建的是原型(prototype)对象时，则在获取对象时才会去创建对象。
#### 1、singleton && lazy-init=false
```java
@Test
public void test6() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
}
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647588500474-df855dba-7368-47ad-bd5a-f1365dfd07c2.png#clientId=u4c720250-2228-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=395&id=u803a2c69&originHeight=395&originWidth=1429&originalType=binary&ratio=1&rotation=0&showTitle=false&size=267912&status=done&style=none&taskId=u8a8047af-224f-4d21-86ae-e0f130aaedc&title=&width=1429)
#### 2、sinleton && lazy-init=true
如果还是上面的测试代码，获取工厂的时候并不会创建Person对象。
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647588703949-78900285-f65e-4eaf-abeb-7e5f88c96370.png#clientId=u4c720250-2228-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=390&id=uae4618fa&originHeight=390&originWidth=1457&originalType=binary&ratio=1&rotation=0&showTitle=false&size=250290&status=done&style=none&taskId=u2f6ae237-b8e4-4894-86d7-a6f9869b719&title=&width=1457)
============>
在获取对象的时候才会去创建对象并且给对象中的成员变量进行赋值。
```java
@Test
public void test6() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
    Person person = ctx.getBean("person", Person.class);
    System.out.println(person);
}
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647588809899-4df5f7c5-d567-4bfc-98e4-1c11565e6add.png#clientId=u4c720250-2228-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=381&id=ue598a80e&originHeight=381&originWidth=1459&originalType=binary&ratio=1&rotation=0&showTitle=false&size=268515&status=done&style=none&taskId=u52ead547-9cbc-49d6-ad55-29734bb7a8e&title=&width=1459)
#### 3、prototype
将`bean`标签中的`scope`属性设置成`prototype`，测试代码和上面一样。
```xml
<bean id="person" class="top.xiaorang.spring.basic.Person" scope="prototype">
    <constructor-arg name="name" value="小让"/>
    <constructor-arg name="age" value="10"/>
</bean> 
```
从下图可知，在获取对象的时候才去创建的对象。
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647589110293-a4240fd8-f16c-44d1-a753-358703275677.png#clientId=u4c720250-2228-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=380&id=u474353ff&originHeight=380&originWidth=1459&originalType=binary&ratio=1&rotation=0&showTitle=false&size=255150&status=done&style=none&taskId=u7b032ee8-9ab2-48a5-8e98-4fb7063249b&title=&width=1459)
### 2、初始化阶段
#### 1、定义
spring工厂在创建完对象后，调用对象的初始化方法，完成对应的初始化操作(资源的初始化, io,connection,网络)。其中的初始化方法根据项目需求由程序员提供，然后交给spring工厂进行调用。
#### 2、两种实现方式

1. 对象需要实现`InitializingBean`接口，然后重写`afterPropertiesSet`方法，这个方法就是完成对象初始化操作的地方；
1. 对象不实现`InitializingBean`接口，而是提供一个普通的初始化方法，放在`bean`标签中的`init-method`属性中。

两种实现方式放在一起测试演示：
```java
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
    System.out.println("Person类的afterPropertiesSet初始化方法" );
  }
    
  public void myInit() {
    System.out.println("自定义的普通方法作为初始化方法" );
  }
}
```
```xml
<bean id="person" class="top.xiaorang.spring.basic.Person" init-method="myInit">
    <property name="name" value="小让"/>
    <property name="age" value="10"/>
</bean>
```
```java
@Test
public void test6() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
    Person person = ctx.getBean("person", Person.class);
    System.out.println(person);
}
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647590067551-df3ca861-6010-423b-a3e9-52be23b6b135.png#clientId=u4c720250-2228-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=537&id=u8c4b4bc5&originHeight=537&originWidth=1462&originalType=binary&ratio=1&rotation=0&showTitle=false&size=358647&status=done&style=none&taskId=u8fa2901e-4ca0-4b28-982c-e25c00a5bd0&title=&width=1462)
:::success
🎨
细节分析：从上面可以看出spring先通过反射调用person构造方法创建对象，然后使用set注入的方式给person对象中的成员变量`name`和`age`进行赋值，然后才执行`initializingBean`接口的`afterPropertiesSet`初始化方法，最后才执行自己定义的`init-method`初始化方法。
:::
### 3、销毁阶段
#### 1、定义
spring销毁对象之前，会调用对象的销毁方法，完成销毁操作(如资源释放，io,connection)。spring会在关闭工厂的时候销毁掉创建出来的对象，其中的销毁操作需要程序员根据需求自定义，然后由spring工厂调用。
#### 2、两种实现方式
和上面的初始化方法一样，一种是实现`DisposableBean`接口，重写`destroy`方法；另一种是自定义销毁方法，然后在`bean`标签中的`destroy-method`属性中声明。
两种方式放在一起测试：
```java
public class Person implements InitializingBean, DisposableBean {
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

  @Override
  public void destroy() throws Exception {
    System.out.println("Person类的destroy销毁方法");
  }

  public void myDestroy() {
    System.out.println("自定义的普通方法作为销毁方法");
  }
}
```
```xml
<bean id="person" class="top.xiaorang.spring.basic.Person" init-method="myInit" destroy-method="myDestroy">
    <property name="name" value="小让"/>
    <property name="age" value="10"/>
</bean>
```
```java
@Test
public void test6() {
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
    Person person = ctx.getBean("person", Person.class);
    System.out.println(person);
    ctx.close();
}
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647591177775-a0b30953-e5d0-409c-8c9f-21c41954f29d.png#clientId=u4c720250-2228-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=544&id=u6878507c&originHeight=544&originWidth=1460&originalType=binary&ratio=1&rotation=0&showTitle=false&size=387950&status=done&style=none&taskId=u74434e78-1a00-46a0-a92e-d54b56e4b2e&title=&width=1460)
**再次完善Bean的生命周期流程**：
:::success
🎨对象从创建->初始化->销毁的完整流程
细节分析：从上面可以看出spring先通过反射调用person构造方法创建对象，然后使用set注入的方式给person对象中的成员变量`name`和`age`进行赋值，然后才执行`initializingBean`接口的`afterPropertiesSet`初始化方法，最后才执行自己定义的`init-method`初始化方法。接下来在关闭工厂的时候，会先执行`DisposableBean`接口的`destroy`方法，然后才执行自定义的销毁方法。
💡注意
销毁方法只针对`signleton`对象。
:::
# 配置文件参数化
## 1、什么叫配置文件参数化？
说白了就是把spring配置文件中需要经常修改的信息维护到另一个专门用于配置的小文件中。这样的话，在以后修改配置的过程中就不需要从spring配置文件中的几千行信息中去查找，而是直接在小配置文件中改动即可，这样也便于维护。
## 2、如何实现？
如前面的数据库连接配置就可以放到一个专门配置的文件中，这个文件就叫`db.properties`。
值得注意的是加个前缀避免重名。
```properties
jdbc.driverClassName=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/atguigudb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
jdbc.username=root
jdbc.password=
```
将`db.properties`文件整合到`spring`配置文件中。
💡**需要在配置文件的**`**beans**`**标签中添加**`**xmlns:context**`**的命名空间，然后还需要在schemaLocation中添加context的xsd**。
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context-4.3.xsd">

<context:property-placeholder location="classpath:/db.properties"/>

<bean id="connection" class="top.xiaorang.spring.factorybean.ConnectionFactoryBean">
    <property name="driverClassName" value="${jdbc.driverClassName}"/>
    <property name="url" value="${jdbc.url}"/>
    <property name="username" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
</bean>
```
```java
@Test
public void test5() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
    Connection connection = ctx.getBean("connection", Connection.class);
    System.out.println(connection);
    Connection connection1 = ctx.getBean("connection", Connection.class);
    System.out.println(connection1);
}
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647593268313-d20e5316-fb2d-4804-8d96-2d984b7e38cd.png#clientId=u4c720250-2228-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=717&id=u3a6fbcda&originHeight=717&originWidth=1463&originalType=binary&ratio=1&rotation=0&showTitle=false&size=543646&status=done&style=none&taskId=u8bdb8439-e5fe-4163-8262-c07033aa529&title=&width=1463)
# 自定义类型转换器
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647596946103-e66b30f4-3e03-49a7-baaa-5ca03d9d513d.png#clientId=u4c720250-2228-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=171&id=rZyA2&originHeight=171&originWidth=1430&originalType=binary&ratio=1&rotation=0&showTitle=false&size=121552&status=done&style=none&taskId=u7b36ad25-7e0f-48fd-9dcb-a376b2e1db4&title=&width=1430)
spring提供了一堆默认的类型转换器，才能把`value`标签中的字符串赋值给对象中的成员变量。在上面给JDK内置类型的成员变量赋值时，提及到如果是`Date`类型，则需要自定义类型，因为spring默认提供的支持日期类型的转换器只支持yyyy/MM/dd的格式，如果想将yyyy-MM-dd格式的日期赋值给`Date`类型的成员变量，此时就得自定义类型转换器。
## 1、自定义类型转换器实现Converter接口
其中默认格式化为"yyyy/MM/dd"，如果想格式化"yyyy-MM-dd"，则在配置文件给成员变量赋值即可。
```java
public class MyDateConverter implements Converter<String, Date> {
  private String pattern = "yyyy/MM/dd";

  @Override
  public Date convert(String source) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      return sdf.parse(source);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }
}
```
## 2、注册自定义Converter
其中，值得注意的是`ConversionServiceFactoryBean`的标签id必须是`conversionService`。
```xml
<bean id="myDateConverter" class="top.xiaorang.spring.converter.MyDateConverter">
    <property name="pattern" value="yyyy-MM-dd"/>
</bean>

<bean id="conversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
    <property name="converters">
        <set>
            <ref bean="myDateConverter"/>
        </set>
    </property>
</bean>
```
## 3、在Person类中增加Date类型的成员变量
```java
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
```
## 4、编写配置文件
```xml
<bean id="person" class="top.xiaorang.spring.basic.Person" init-method="myInit" destroy-method="myDestroy">
  <property name="name" value="小让"/>
  <property name="age" value="10"/>
  <property name="birthday" value="2022-03-18"/>
</bean>
```
## 5、测试
```java
@Test
public void test1() {
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("/applicationContext.xml");
    Person person = applicationContext.getBean("person", Person.class);
    System.out.println(person);
}
```
此时可以看到，日期类型就转换成功，并且赋值到`birthday`成员变量当中。
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647596779983-bb495e61-ee5e-4108-a2d7-99acbf56214e.png#clientId=u4c720250-2228-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=815&id=u409697cd&originHeight=815&originWidth=1459&originalType=binary&ratio=1&rotation=0&showTitle=false&size=621607&status=done&style=none&taskId=ue80f658a-c414-4834-8c9e-246e035c7ed&title=&width=1459)
# BeanPostProcessor(后置处理Bean)
## 1、BeanPostProcessor接口干嘛用的？
对Spring工厂创建的对象进行再加工。
## 2、BeanPostProcessor接口的运行时机
**再次完善Bean的生命周期流程**：
:::success
🎨对象从创建->初始化->销毁的完整流程
细节分析：从上面可以看出spring先通过反射调用person构造方法创建对象，然后使用set注入的方式给person对象中的成员变量`name`和`age`进行赋值，然后执行`BeanPostProcessor#postProcessBeforeInitialization`方法，然后才执行`initializingBean`接口的`afterPropertiesSet`初始化方法，最后才执行自己定义的`init-method`初始化方法和`BeanPostProcessor#postProcessAfterInitialization`方法。接下来在关闭工厂的时候，会先执行`DisposableBean`接口的`destroy`方法，然后才执行自定义的销毁方法。
💡注意
销毁方法只针对`signleton`对象。
:::
可以看到BeanPostProcessor接口定义了两个方法，一个`postProcessBeforeInitialization`，运行在`initializingBean`接口的`afterPropertiesSet`初始化方法之前；另一个`postProcessAfterInitialization`方法则运行在自己定义的`init-method`初始化方法之后。
Spring中的AOP底层实现就是在`postProcessAfterInitialization`方法中使用jdk的动态代理对Bean进行增强。
## 3、开发步骤
### 1、实现BeanPostProcessor接口
```java
public class MyBeanPostProcessor implements BeanPostProcessor {
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("使用BeanPostProcessor接口中的after方法对 " + beanName + "进行增强");
    return bean;
  }
}
```
### 2、编写配置文件
```xml
<bean class="top.xiaorang.spring.ioc.beanpostprocessor.MyBeanPostProcessor" />
```
### 3、测试
```java
@Test
public void test1() {
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("/applicationContext.xml");
    Person person = applicationContext.getBean("person", Person.class);
    System.out.println(person);
}
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1554080/1647934951581-9335363b-7298-40c8-a1a3-aa7b7027c27b.png#clientId=ucf18156f-7f8c-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=875&id=ueb0566f2&originHeight=875&originWidth=1459&originalType=binary&ratio=1&rotation=0&showTitle=false&size=182689&status=done&style=none&taskId=ue7cb6f52-e81b-44a3-9c20-f8dfd901f04&title=&width=1459)
### 4、代码分析

1. 从上图可以看出`BeanPostProcessor#postProcessAfterInitialization`方法运行在自定义的`init-method`方法之后。
1. `BeanPostProcessor`会对Spring工厂中创建的所有Bean对象进行增强。
