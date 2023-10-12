package com.dhf.aspect;

import com.dhf.annotation.MyAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/4/1/0001 23:28
 */
@Slf4j
@Aspect
@Component
public class MyAnnotationAspect {
    @Around("@annotation(myAnnotation)")
    public Object doSomeThing(ProceedingJoinPoint joinPoint, MyAnnotation myAnnotation) throws Throwable {
        String remarks = myAnnotation.remarks();
        log.info("remarks: {}", remarks);
        return "ok";
    }
}
