package ru.gb.timesheet.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

  private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

  @Pointcut("execution(* ru.gb.timesheet.service.TimesheetService.*(..))")
  public void timesheetServiceMethods() {}

  @Before("timesheetServiceMethods()")
  public void logMethodArguments(JoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();
    Arrays.stream(args).forEach(arg -> {
      log.info(String.format("%s = %s", arg.getClass().getSimpleName(), arg));
      if (arg.getClass().isArray()) {
        Object[] array = ((Object[]) arg);
        Arrays.stream(array).forEach(obj -> {
          log.info(String.format("%s = %s", obj.getClass().getSimpleName(), obj));
        });
      }
    });
  }

  @AfterThrowing(pointcut = "timesheetServiceMethods()", throwing = "e")
  public void logException(JoinPoint joinPoint, Exception e) {
    log.error(String.format("Recovering %s#%s after Exception[%s, \"%s\"]",
            joinPoint.getTarget().getClass().getSimpleName(),
            joinPoint.getSignature().getName(),
            e.getClass().getSimpleName(),
            e.getMessage()));
  }
}
