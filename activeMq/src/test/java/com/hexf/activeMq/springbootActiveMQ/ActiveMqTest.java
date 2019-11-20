package com.hexf.activeMq.springbootActiveMQ;


import com.hexf.activeMq.springbootActiveMQ.service.MqConsumer;
import com.hexf.activeMq.springbootActiveMQ.service.MqProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@SpringBootTest(classes = SBActiveMQApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ActiveMqTest {

    @Resource
    private MqProducer producer;

    @Test
    public void testProducer() {
        producer.produceMsg();
    }

    @Test
    public void testProducerScheduled() {
        producer.produceMsgScheduled();
    }

}
