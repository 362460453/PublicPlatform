package com.demo.goods;

import com.demo.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * @author yanlin
 * @version v1.3
 * @date 2019-06-20 4:13 PM
 * @since v8.0
 **/
@Service
public class AsyncConfirmConsumer {
    @RabbitListener(queues = "confirm_queue")
    @RabbitHandler
    public void asyncConfirm(Order order, Message message, Channel channel) throws IOException {

        try {
            System.out.println("消费消息：" + order.getName());
//            int a = 1 / 0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            System.out.println("消费消息确认" + message.getMessageProperties().getConsumerQueue() + "，接收到了回调方法");
        } catch (Exception e) {
            //重新回到队列
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//            System.out.println("尝试重发：" + message.getMessageProperties().getConsumerQueue());
            //requeue =true 重回队列，false 丢弃
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            // TODO 该消息已经导致异常，重发无意义，自己实现补偿机制


        }


    }
}
