package com.hexf.activeMq.normal;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class Producer {

//    private final static String URL = "tcp://10.161.61.141:61616";
    private final static String URL = "nio://10.161.61.141:61618";
//    private final static String URL = "tcp://localhost:61616";
    private final static String QUEUE_NAME = "queue1";
    public static void main(String[] args) {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);;
            Connection connection = factory.createConnection();
            connection.start();

            // 两个参数，事务、签收； 当为true,即开启事务时，先执行MessageProducer.send()，然后执行Session.commit()消息才会提交到队列中去
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(QUEUE_NAME);
            MessageProducer producer = session.createProducer(queue);

            // 消息的持久化设置，默认是持久化的
//            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT); // 服务器宕机重启之后，消息消失
//            producer.setDeliveryMode(DeliveryMode.PERSISTENT);  // 服务器宕机，重启后消息依旧存在，但是会出队

            for (int i = 0; i < 3; i ++) {
                TextMessage textMessage = session.createTextMessage(i + "***");
                producer.send(textMessage);
            }

            producer.close();
            session.commit();  // 事务开启时，必须要commit才可以提交到队列中
            session.close();
            connection.close();

            System.out.println("***************Produce End************");
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
