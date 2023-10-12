package com.dhf.annotation;

import com.dhf.annotation.validator.ParamCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/15/0015 11:17
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
// 官方校验注解
@Constraint(validatedBy = {ParamCheckValidator.class})
public @interface ParamCheck {
    String message() default "参数校验失败";
    // 分组校验
    Class<?>[] groups() default {};

    // 负载信息，可以在验证时携带其他有效负载数据。
    Class<? extends Payload>[] payload() default {};
}
