package com.afair;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author WangBing
 * @date 2020/12/31 11:14
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTest {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void sendMsg() {
        kafkaTemplate.send("foo", "wo shi xiao ben da");
    }
}
