package com.hexf.activeMq.normal;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.xml.soap.Text;
import java.io.IOException;

/**
 * 主题持久化客户端
 *
 */
public class Consumer_topic_persistent {

    private final static String URL = "tcp://10.161.61.141:61616";
    private final static String TOPIC_NAME = "topic1";

    public static void main(String[] args)  {

        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);

            Connection connection = factory.createConnection();
            connection.setClientID("hexf");  // 持久化订阅必须要有clientID，而且connection必须是初次使用才可以

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic(TOPIC_NAME);

            TopicSubscriber subscriber = session.createDurableSubscriber(topic, "test...");
            connection.start();
            Message message = subscriber.receive();
            while (null != message) {
                TextMessage txtMessage = (TextMessage)message;
                System.out.println("持久化消费消息：" + txtMessage.getText());
                message = subscriber.receive(4000L);
            }

            session.close();
            connection.close();
        }catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
