package com.producer.entity;

import lombok.Data;

@Data
public class MessageInfo<T> {
    /**
     * producer发送消息的方式，sync、async
     */
    private String flush;
    private String topic;
    private String group;
    /**
     * 消息重复提交生产者的次数
     */
    private int reProductNums;
    /**
     * 消息
     */
    private T message;
    /**
     * 向指定队列中发送消息标识
     */
    private boolean sendByQueueIdFlag = false;
}
