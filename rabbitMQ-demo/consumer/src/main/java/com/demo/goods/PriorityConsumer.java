package com.demo.goods;

import com.demo.Order;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-06-17 4:54 PM
 * @since v8.0
 **/
@Service
public class PriorityConsumer {
    /**
     * 延迟消费方法
     *
     * @param order
     */
    @RabbitListener(queues = "priority_queue")
    @RabbitHandler
    public void delay(Order order) {
        System.out.println(order.getId());
    }
}
