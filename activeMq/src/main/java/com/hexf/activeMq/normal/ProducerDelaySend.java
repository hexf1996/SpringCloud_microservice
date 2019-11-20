package com.hexf.activeMq.normal;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;


/**
 * 定时和延时投递，可参见官网：http://activemq.apache.org/delay-and-schedule-message-delivery
 * 1、修改配置文件，设置broker的 schedulerSupport="true"
 * 2、在生产者中设置TextMessage的延时周期时间属性
 */
public class ProducerDelaySend {

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

            long delay = 3 * 1000;
            long period = 3 * 1000;
            int repeatTimes = 4;

            for (int i = 0; i < 3; i ++) {
                TextMessage textMessage = session.createTextMessage(i + "***");
                textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
                textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
                textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeatTimes);
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
