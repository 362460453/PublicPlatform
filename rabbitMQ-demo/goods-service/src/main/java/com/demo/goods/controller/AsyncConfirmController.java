package com.demo.goods.controller;

import com.demo.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-06-20 4:12 PM
 * @since v8.0
 **/
@RestController
public class AsyncConfirmController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/async/{id}")
    public String AETest(@PathVariable Integer id) {
        Order order = new Order(id, "胖虎");
        rabbitTemplate.convertAndSend("confirm_exchange", "", order);
        return "成功";
    }
}
