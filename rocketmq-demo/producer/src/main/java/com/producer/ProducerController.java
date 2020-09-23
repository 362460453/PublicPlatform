package com.producer;

import com.producer.entity.MessageInfo;
import com.producer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生产者
 */
@RestController
@SpringBootApplication
public class ProducerController {
    public static void main(String[] args) {
        SpringApplication.run(ProducerController.class, args);
    }
    @Autowired
    ProductMessage productMessage;

    @GetMapping(value = "/product/sync")
    public String productSyncMessage() {
        User user = new User().setId(1).setUserName("panghu").setPwd("sync");
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setMessage(user);
        messageInfo.setFlush("0");
        messageInfo.setTopic("topic-2");
        productMessage.product(messageInfo);
        return "produceResult";
    }

    @GetMapping(value = "/product/async")
    public String productAsyncMessage() {
        User user = new User().setId(1).setUserName("panghu").setPwd("async");
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setMessage(user);
        messageInfo.setTopic("topic-2");
        messageInfo.setFlush("1");
        productMessage.product(messageInfo);
        return "produceResult";
    }

}
