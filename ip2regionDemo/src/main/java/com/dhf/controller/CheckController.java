package com.dhf.controller;

import com.dhf.annotation.ParamCheck;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/15/0015 11:28
 */
@Controller
@Validated // 开启校验
public class CheckController {
    @ResponseBody
    @GetMapping("/check")
    public Object check(@ParamCheck String param) {
        return param;
    }
}
