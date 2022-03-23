package top.xiaorang.spring.aop.proxy.staticproxy;

public class TeacherServiceProxy implements TeacherService {
  private TeacherService teacherService;

  @Override
  public void teach() {
    if (teacherService == null) {
      teacherService = new TeacherServiceImpl();
    }
    before();
    teacherService.teach();
    after();
  }

  private void before() {
    System.out.println("调用目标对象方法之前的方法");
  }

  private void after() {
    System.out.println("调用目标对象方法之之后的方法");
  }

  public TeacherService getTeacherService() {
    return teacherService;
  }

  public void setTeacherService(TeacherService teacherService) {
    this.teacherService = teacherService;
  }
}
