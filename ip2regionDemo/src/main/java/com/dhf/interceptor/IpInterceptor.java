package com.dhf.interceptor;

import com.dhf.utils.ut.IpUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/14/0014 11:52
 */

@Component("ipInterceptor")
public class IpInterceptor implements HandlerInterceptor {
    private static final String DEFAULT_KEY = "city";
    /**
     * 通过 ip 获取城市信息，并将城市信息存入 request session 中
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute(DEFAULT_KEY) == null){
            // 获取请求的ip地址
            String remoteIp = request.getRemoteAddr();
            String cityByIp = IpUtil.getCityByIp(remoteIp);
            // 将 city 存入 request session 中
            request.getSession().setAttribute(DEFAULT_KEY, cityByIp);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
