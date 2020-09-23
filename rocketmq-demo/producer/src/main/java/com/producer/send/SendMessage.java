package com.producer.send;

import com.producer.entity.MessageInfo;

/**
 * @Description: 向RocketMQ发送消息的接口
 */
public interface SendMessage {

    /**
     * 发送消息抽象方法
     *
     * @param messageInfo 消息实体
     * @return boolean 执行结果
     */
    boolean sendMessage(final MessageInfo messageInfo);

}
