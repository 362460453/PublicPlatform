package com.demo.listener;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-06-20 4:13 PM
 * @since v8.0
 **/
@Configuration
public class AsyncConfirmListener {

    @Bean
    public Queue confirmQueue() {
        return new Queue("confirm_queue");
    }

    @Bean
    public FanoutExchange confirmExchange() {
        return new FanoutExchange("confirm_exchange");
    }

    //交换器绑定队列
    @Bean
    Binding bindingExchangeConfirm(Queue confirmQueue, FanoutExchange confirmExchange) {
        return BindingBuilder.bind(confirmQueue).to(confirmExchange);
    }
}
