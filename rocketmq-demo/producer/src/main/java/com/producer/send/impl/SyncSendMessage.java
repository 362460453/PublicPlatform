package com.producer.send.impl;

import com.producer.entity.MessageInfo;
import com.producer.send.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: 以同步的方式发送消息至rocketmq
 */
@Component
@Slf4j
public class SyncSendMessage implements SendMessage {

    @Resource
    RocketMQTemplate rocketMQTemplate;
    @Autowired
    Environment environment;

    @Override
    public boolean sendMessage(MessageInfo messageInfo) {
        boolean result = true;
//        try {
        // 发送消息
        SendResult sendResult;
        sendResult = rocketMQTemplate.syncSend("topic-2", messageInfo);
        log.info("template sendresult:{}", sendResult.toString());
        // 处理发送结果
        result = this.handleSendResult(sendResult, messageInfo);
//        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
//            log.error("messageInfo:{} Exception:{}", messageInfo.toString(), e);
//            result = false;
//            Thread.currentThread().interrupt();
//        }
        return result;
    }

    /**
     * 处理Product Message的结果
     *
     * @param sendResult
     * @param messageInfo
     */
    protected boolean handleSendResult(SendResult sendResult, final MessageInfo messageInfo) {
        SendStatus status = sendResult.getSendStatus();
        //发送成功和从节点不可用两种情况消息都已经发送至broker上
        if (status.equals(SendStatus.SEND_OK) || status.equals(SendStatus.SLAVE_NOT_AVAILABLE)) {

            return true;
        } else if (status.equals(SendStatus.FLUSH_DISK_TIMEOUT) || status.equals(SendStatus.FLUSH_SLAVE_TIMEOUT)) {

        }
        return false;
    }

}
