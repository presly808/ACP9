package ua.artcode.eshop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by serhii on 13.11.15.
 */
@Aspect
@Component
public class LoggingAspect {

    @Before("allServiceMethodsPointCut()")
    public void logCallingServiceMethodsJoinPoint(){
        System.out.println("Method calling, log message");
    }

    @Pointcut("execution(* ua.artcode.eshop.service.*.*(..))")
    public void allServiceMethodsPointCut(){

    }

}
