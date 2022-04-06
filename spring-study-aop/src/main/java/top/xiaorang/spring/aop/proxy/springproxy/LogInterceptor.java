package top.xiaorang.spring.aop.proxy.springproxy;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

public class LogInterceptor {
  public void beforeMethod(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    System.out.println(
        "The method " + methodName + " begins with " + Arrays.asList(joinPoint.getArgs()));
  }

  public void afterMethod(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    System.out.println("The method " + methodName + " ends");
  }

  public void afterReturning(JoinPoint joinPoint, Object result) {
    String methodName = joinPoint.getSignature().getName();
    System.out.println("The method " + methodName + " ends with " + result);
  }

  public void afterThrowing(JoinPoint joinPoint, Exception e) {
    String methodName = joinPoint.getSignature().getName();
    System.out.println("The method " + methodName + " occurs excetion:" + e);
  }
}
