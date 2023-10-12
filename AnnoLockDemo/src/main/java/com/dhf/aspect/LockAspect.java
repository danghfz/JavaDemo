package com.dhf.aspect;

import com.dhf.annotation.Lock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import com.dhf.server.LockServer;

import java.lang.reflect.Method;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/25/0025 22:36
 */
@Aspect
@Component
public class LockAspect {
    private static LockServer lockServer = new LockServer();
    @Around("@annotation(com.dhf.annotation.Lock)")
    public Object aroundMethodExecution(ProceedingJoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Lock annotation = method.getAnnotation(Lock.class);
        // 可以获取到注解的信息，做一些其他操作
        System.out.println("hello aspect");
        return lockServer.execute(() -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
                return null;
            }
        });
    }
}
