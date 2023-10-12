package com.dhf.controller;

import com.dhf.server.TestServer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/26/0026 9:12
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {
    private final TestServer testServer;
    @RequestMapping("/test")
    public String test() throws InterruptedException {
        testServer.test();
        return "test";
    }
}
