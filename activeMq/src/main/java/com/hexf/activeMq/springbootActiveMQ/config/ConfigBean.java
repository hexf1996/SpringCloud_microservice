package com.hexf.activeMq.springbootActiveMQ.config;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
@EnableJms
public class ConfigBean {

    @Value("${myqueue}")
    private String myqueue;

    @Bean
    private Queue getQueue() {
        return new ActiveMQQueue(myqueue);
    }

}
