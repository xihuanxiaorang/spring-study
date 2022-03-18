package top.xiaorang.spring.ioc.basic;

public class UserServiceImpl implements UserService {
  private UserDao userDao;

  @Override
  public void saveAllUsers() {
    userDao.saveAllUsers();
  }

  public UserDao getUserDao() {
    return userDao;
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }
}
