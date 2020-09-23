package com.consumer.service;

import com.consumer.entity.MessageInfo;

public interface RocketMQListener {
    void onMessage(MessageInfo message);
}
