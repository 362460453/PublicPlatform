package com.demo.listener;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-06-17 4:54 PM
 * @since v8.0
 **/
@Configuration
public class PriorityListener {
    private static final String PRIORITY_QUEUE= "priority_queue";
    @Bean
    public Queue priorityQueue() {
        Map<String, Object> map = new HashMap<>();
        //给当前队列配置最大优先级
        map.put("x-max-priority", 10);
        return new Queue(PRIORITY_QUEUE, true, false, false, map);
    }

    @Bean
    public FanoutExchange exchange(){
        return new FanoutExchange("priority_exchange");
    }

    @Bean
    public Binding priorityBinding(Queue priorityQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(priorityQueue).to(exchange);
    }
}
