package com.client.controller;


import com.annotation.RateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-04-05 8:10 PM
 * @since v8.0
 **/
@RestController
public class LimitController {


    @RateLimit(key = "test", time = 10, count = 10)
    @GetMapping("/test/limit")
    public String testLimit() {
        return "Hello,ok";
    }

    @RateLimit()
    @GetMapping("/test/limit/a")
    public String testLimitA() {
        return "Hello,ok";
    }
}
