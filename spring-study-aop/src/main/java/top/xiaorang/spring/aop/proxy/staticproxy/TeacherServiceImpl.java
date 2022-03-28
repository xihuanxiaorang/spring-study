package top.xiaorang.spring.aop.proxy.staticproxy;

public class TeacherServiceImpl implements TeacherService {
  @Override
  public void teach() {
    System.out.println("教课");
  }

  public static void say() {
    System.out.println("说话");
  }
}
