package com.producer;

import com.producer.entity.MessageInfo;
import com.producer.send.impl.SyncSendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.producer.send.impl.AsyncSendMessage;


/**
 * @Description: 生产者消息发送实体类
 * @Author: houth
 * @Date: 2018/10/9
 */
@Component
@Slf4j
public class ProductMessage {
    @Autowired
    AsyncSendMessage asyncSendMessage;
    @Autowired
    SyncSendMessage syncSendMessage;

    /**
     * @Description: 根据不同的发送方式和配置发送消息至消息队列
     */
    public String product(final MessageInfo messageInfo) {
        boolean result = true;
        String resultCode = "00";
        switch (messageInfo.getFlush()) {
            case "0":
                result = syncSendMessage.sendMessage(messageInfo);
                break;
            case "1":
                result = asyncSendMessage.sendMessage(messageInfo);
                break;
            default:
                log.error("messageInfo Does not contain the correct flush. messageInfo:{}", messageInfo.toString());
                break;
        }
        if (!result) {
            resultCode = "11";
        }
        return resultCode;
    }
}
