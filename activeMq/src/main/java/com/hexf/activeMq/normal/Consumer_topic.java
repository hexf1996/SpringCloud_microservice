package com.hexf.activeMq.normal;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class Consumer_topic {

    private final static String URL = "tcp://10.161.61.141:61616";
    private final static String TOPIC_NAME = "topic1";

    public static void main(String[] args)  {

        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);

            Connection connection = factory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic(TOPIC_NAME);

            MessageConsumer consumer = session.createConsumer(topic);

            // 方法1： receive()，同步阻塞方式
            /*while (true) {
//                TextMessage message = (TextMessage)consumer.receive();
                TextMessage message = (TextMessage)consumer.receive(3000L);
                if (null != message) {
                    System.out.println("-----------Message from Producer is:" + message.getText());
                } else
                    break;
            }*/

            // 方法2：监听的方式，异步非阻塞方式，系统自动用消息监听器MessageListener的onMessage()方法消费消息
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if (null != message && message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage)message;
                        try {
                            System.out.println("-----------Message from Producer is:" + textMessage.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            System.in.read();  // Press any key to exit.防止因为消息太大没有读完而直接退出的问题

            consumer.close();
            session.close();
            connection.close();
        }catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
