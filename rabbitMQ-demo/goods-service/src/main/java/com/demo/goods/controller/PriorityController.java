package com.demo.goods.controller;

import com.demo.Order;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-06-17 4:55 PM
 * @since v8.0
 **/
@RestController
public class PriorityController {

    @Autowired
    private AmqpTemplate amqpTemplate;
    @GetMapping("/priority/{id}")
    public String delayTest(@PathVariable Integer id) {
        for (int i=0;i<10;i++){
            Order order = new Order(i, "第"+i+"个");
            int finalI = i;
            amqpTemplate.convertAndSend("priority_exchange", "", order, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setPriority(finalI);
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.fromInt(2));
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    return message;
                }
            });
        }

        return "成功";
    }
}
