package com.hexf.activeMq.broker;

import org.apache.activemq.broker.BrokerService;

/**
 * Broker实例
 */
public class EmbedBroker {

    public static void main(String[] args) {
        try {
            BrokerService service = new BrokerService();
            service.setUseJmx(true);
            service.addConnector("tcp://localhost:61616");
            service.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
