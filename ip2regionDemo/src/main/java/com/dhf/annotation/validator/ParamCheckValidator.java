package com.dhf.annotation.validator;

import com.dhf.annotation.ParamCheck;

import javax.validation.ConstraintValidator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/15/0015 11:23
 */
public class ParamCheckValidator implements ConstraintValidator<ParamCheck, String> {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    @Override
    public boolean isValid(String value, javax.validation.ConstraintValidatorContext context) {
        // 校验逻辑
        if (value == null || isEmail(value)) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("参数不是有效的邮箱地址").addConstraintViolation();
        return false;
    }
    public boolean isEmail(String value) {
        // 校验逻辑
        Pattern compile = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = compile.matcher(value);
        return matcher.matches();
    }
}
