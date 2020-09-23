package com.consumer.service.impl;

import com.consumer.entity.MessageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RocketMQMessageListener(topic = "topic-2", consumerGroup = "my-group")
public class UserConsumer implements RocketMQListener<MessageInfo> {

    @Override
    public void onMessage(MessageInfo message) {
        log.info("收到消息:{}", message.toString());
    }
}
