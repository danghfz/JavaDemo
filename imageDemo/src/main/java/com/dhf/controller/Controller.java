package com.dhf.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/07/28 21:03
 */
@RestController
public class Controller {
    @GetMapping(value = "/",produces = MediaType.IMAGE_PNG_VALUE)
    public Object test() throws Exception{
        String path = Objects.requireNonNull(Controller.class.getResource("/img.png")).getPath();
        FileInputStream inputStream = new FileInputStream(new File(path));
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes,0,inputStream.available());
        return bytes;
    }
}
