package com.hexf.activeMq.normal;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class Consumer {

//    private final static String URL = "tcp://10.161.61.141:61616";
    private final static String URL = "nio://10.161.61.141:61618";
//    private final static String URL = "tcp://localhost:61616";
    private final static String QUEUE_NAME = "queue1";

    public static void main(String[] args)  {

        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);

            Connection connection = factory.createConnection();
            connection.start();

            // 当事务参数设置为true时，若不commit，则消息会被重复消费，即消费者消费之后第二次还会消费到，即消息的重复消费
//             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 签收模式为手动签收模式时，消息会被重复消费
             Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            Queue queue = session.createQueue(QUEUE_NAME);

            MessageConsumer consumer = session.createConsumer(queue);

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
            consumer.setMessageListener((Message message) -> {
                if (null != message && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage)message;
                    try {
                        System.out.println("-----------Message from Producer is:" + textMessage.getText());
                        textMessage.acknowledge(); // 签收模式为手动签收时，进行手动签收消息
                    } catch (JMSException e) {
                        e.printStackTrace();
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
