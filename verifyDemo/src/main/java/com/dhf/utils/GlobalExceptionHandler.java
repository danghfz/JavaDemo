package com.dhf.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/07/13 8:55
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultAPI> handleException(Exception ex) {
        // 创建自定义的错误响应对象
        ResultAPI res = new ResultAPI();
        res.setMessage("Internal Server Error")
                .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setDate("exception: ", ex.getMessage());
        // 返回适当的HTTP状态码和错误响应
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
