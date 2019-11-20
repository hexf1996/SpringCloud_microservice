package com.hexf.activeMq.springbootActiveMQ.service;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component
public class MqConsumer {

    @JmsListener(destination = "${myqueue}")
    public void receive(TextMessage message) throws Exception{
        System.out.println("Message is:" + message.getText());
    }

}
