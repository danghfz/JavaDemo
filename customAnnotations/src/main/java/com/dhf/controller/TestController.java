package com.dhf.controller;

import com.dhf.annotation.MyAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/4/1/0001 23:36
 */
@RestController
public class TestController {
    @RequestMapping("/hello")
    @MyAnnotation(remarks = "hello方法")
    public String hello() {
        return "hello";
    }
}
