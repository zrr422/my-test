package com.zrr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author zhangrongrong
 * @Date 2022/7/25 14:54
 * @ClassName: Application
 * @Description: 启动类
 */
@SpringBootApplication
@ComponentScan("com.zrr")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
