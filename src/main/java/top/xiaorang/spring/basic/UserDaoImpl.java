package top.xiaorang.spring.basic;

public class UserDaoImpl implements UserDao {
  @Override
  public void saveAllUsers() {
    System.out.println("保存所有的用户");
  }
}
