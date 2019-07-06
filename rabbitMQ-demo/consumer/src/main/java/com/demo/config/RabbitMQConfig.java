package com.demo.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;


/**
 * @author yanlin
 * @version v1.3
 * @date 2019-06-14 11:36 AM
 * @since v8.0
 **/
@Service
public class RabbitMQConfig implements ChannelAwareMessageListener {


    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("消费者成功消费了" + message.getBody().toString() + "，接收到了回调方法");
        //在未确认消费的集合中删掉一条时multiple参数用false
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
