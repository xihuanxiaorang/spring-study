package top.xiaorang.spring.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
