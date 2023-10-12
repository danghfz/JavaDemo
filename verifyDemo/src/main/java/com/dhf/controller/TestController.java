package com.dhf.controller;

import com.dhf.domain.People;
import com.dhf.utils.ResultAPI;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/07/13 8:52
 */
@Controller
public class TestController {
    /**
     * 校验people参数
     * @param people 参数
     * @return ok？
     */
    @PostMapping("/test")
    @ResponseBody
    public ResultAPI test(@Validated @RequestBody People people) {
        /*
         * 检查people的各个参数
         */
        return ResultAPI.ok();
    }
}
