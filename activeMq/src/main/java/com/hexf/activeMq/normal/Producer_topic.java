package com.hexf.activeMq.normal;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class Producer_topic {

    private final static String URL = "tcp://10.161.61.141:61616";
    private final static String TOPIC_NAME = "topic1";
    public static void main(String[] args) {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
            Connection connection = factory.createConnection();
//            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(TOPIC_NAME);
            MessageProducer producer = session.createProducer(topic);

            // 持久化
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            connection.start();

            for (int i = 0; i < 3; i ++) {
                TextMessage textMessage = session.createTextMessage(i + "***");
                producer.send(textMessage);
            }

            producer.close();
            session.close();
            connection.close();

            System.out.println("***************TOPIC: Produce End************");
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
