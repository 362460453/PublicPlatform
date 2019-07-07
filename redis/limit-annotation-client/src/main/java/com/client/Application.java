package com.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yanlin
 * @version v1.0
 * @className Application
 * @description TODO
 * @date 2019-07-07 12:21 PM
 **/
@SpringBootApplication
@ComponentScan(value = {"com.annotaion", "com.client", "com.config"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
