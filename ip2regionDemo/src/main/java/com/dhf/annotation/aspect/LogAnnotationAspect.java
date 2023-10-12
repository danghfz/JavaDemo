package com.dhf.annotation.aspect;

import com.dhf.annotation.LogAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/14/0014 12:02
 */
@Aspect
@Component
public class LogAnnotationAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAnnotationAspect.class);

    @Before("@annotation(logAnnotation)")
    public void beforeMethodExecution(JoinPoint joinPoint, LogAnnotation logAnnotation) {
        // 获取当前的方法名称
        String methodName = joinPoint.getSignature().getName();
        // 当前系统时间 yyyy/MM/dd HH:mm:ss
        // 创建日期对象
        Date currentDate = new Date();
        // 创建日期格式化对象，并指定输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        // 格式化日期对象为字符串
        String formattedDate = dateFormat.format(currentDate);
        LOGGER.info("方法 {} 在 {} 被调用", methodName, formattedDate);
    }
}
