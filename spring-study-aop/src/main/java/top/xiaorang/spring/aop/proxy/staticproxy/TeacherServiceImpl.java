package top.xiaorang.spring.aop.proxy.staticproxy;

public class TeacherServiceImpl implements TeacherService {
  @Override
  public void teach() {
    System.out.println("教课");
  }
}
