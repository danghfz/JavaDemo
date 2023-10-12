package com.dhf.controller;

import com.dhf.annotation.LogAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/14/0014 12:11
 */
@Controller
public class IpController {
    @ResponseBody
    @RequestMapping("/")
    @LogAnnotation
    public Object test(HttpServletRequest request) {
        Object city = request.getSession().getAttribute("city");
        return city;
    }
}
