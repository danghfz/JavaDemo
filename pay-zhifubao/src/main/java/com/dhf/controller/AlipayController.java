package com.dhf.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author danghf
 * @data 2024/2/20 20:09
 */
@RestController
@RequestMapping("/alipay")
@Slf4j
public class AlipayController {
    // http://localhost:9000/alipay/pay?subject=123&outTradeNo=123456&totalAmount=0.01
    @GetMapping("/pay")
    public String pay(HttpServletRequest request){
        AlipayTradePagePayResponse response;
        try {
            // 创建当面付收款二维码
            response = Factory.Payment.Page()
                    // String subject, String outTradeNo, String totalAmount, String returnUrl
                    .pay(
                            // 主题
                            request.getParameter("subject"),
                            // 订单号
                            request.getParameter("outTradeNo"),
                            // 总金额
                            request.getParameter("totalAmount"),
                            ""
                    );
        }catch (Exception e){
            throw new RuntimeException("pay error"+e.getMessage(), e);
        }
        return response.body;
    }

    @PostMapping("/notify")
    public String payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=================支付宝异步回调=================");
            HashMap<String, String> params = new HashMap<>();
            request.getParameterMap().forEach((k, v) -> params.put(k, v[0]));
            String outTradeNo = params.get("out_trade_no");
            String tradeNo = params.get("trade_no");
            String gmtPayment = params.get("gmt_payment");
            // 支付宝验签
            if (Factory.Payment.Common().verifyNotify(params)){
                // 验签成功
                log.info("支付宝异步回调成功，订单号：{}，支付宝交易号：{}，支付时间：{}, 交易状态: {}", outTradeNo, tradeNo, gmtPayment, params.get("trade_status"));
            }
            // 更新订单信息
        }
        return "success";
    }
}
