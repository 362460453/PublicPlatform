package com.demo.goods;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author yanlin
 * @version v1.3
 * @date 2019-06-20 5:39 PM
 * @since v8.0
 **/
@Component
public class RabbitTemplateConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate() {
        // 设置生产者消息确认
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
     *
     * @param correlationData
     * @param b
     * @param s
     */
    @Override
    public void confirm(@Nullable CorrelationData correlationData, boolean b, @Nullable String s) {
        System.out.println("ack：[{}]" + b);
        if (b) {
            System.out.println("消息到达rabbitmq服务器");
        } else {
            System.out.println("消息可能未到达rabbitmq服务器");
        }
    }

    /**
     * 启动消息失败返回，比如路由不到队列时触发回调
     *
     * @param message
     * @param i
     * @param s
     * @param s1
     * @param s2
     */
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("消息主体 message : " + message);
        System.out.println("消息主体 replyCode : " + i);
        System.out.println("描述 replyText：" + s);
        System.out.println("消息使用的交换器 exchange : " + s1);
        System.out.println("消息使用的路由键 routing : " + s2);
    }
}
