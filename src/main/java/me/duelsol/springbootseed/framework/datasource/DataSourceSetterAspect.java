package me.duelsol.springbootseed.framework.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author 冯奕骅
 */
@Aspect
@Component
public class DataSourceSetterAspect {

    @Pointcut("execution(* me.duelsol.springbootseed.service..*.*(..))")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource dataSource = method.getAnnotation(DataSource.class);
            DataSourceHolder.setDataSource(dataSource.value());
        }
    }

    @AfterReturning(value = "pointcut()")
    public void afterReturning() {
        DataSourceHolder.clear();
    }

}
