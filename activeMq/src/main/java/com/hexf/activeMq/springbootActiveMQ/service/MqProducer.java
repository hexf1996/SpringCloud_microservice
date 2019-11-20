package com.hexf.activeMq.springbootActiveMQ.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

@Component
public class MqProducer {

    @Autowired
    private JmsMessagingTemplate template;

    @Autowired
    private Queue queue;

    public void produceMsg() {
        template.convertAndSend(queue, "生产消息：" + UUID.randomUUID().toString());
    }

    // 定时3s自动投递
    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled() {
        template.convertAndSend(queue, "生产消息：" + UUID.randomUUID().toString());
    }

}
