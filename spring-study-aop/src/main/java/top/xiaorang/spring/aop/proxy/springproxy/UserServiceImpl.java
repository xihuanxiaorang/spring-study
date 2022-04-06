package top.xiaorang.spring.aop.proxy.springproxy;

public class UserServiceImpl implements UserService {
  @Override
  public void login(String name, String password) {
    System.out.println(name + "用户登录！");
  }
}
