package com.dhf;

import com.dhf.config.MyConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/07/26 9:06
 * @EnableConfigurationProperties 开启配置属性支持
 */
@SpringBootApplication
@EnableConfigurationProperties(value = {MyConfig.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired)) // 注入 final
public class Main implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public final MyConfig myConfig;
    //@Autowired
    //public MyConfig myConfig;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            log.info("myConfig: {}", myConfig);
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
        }
    }
}
