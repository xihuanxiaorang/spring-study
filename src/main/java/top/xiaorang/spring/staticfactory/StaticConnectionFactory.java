package top.xiaorang.spring.staticfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
