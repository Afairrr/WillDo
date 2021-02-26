package com.afair.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author WangBing
 * @date 2020/12/31 10:05
 */
@Slf4j
public class Listener {

    @KafkaListener(topics = {"foo"})
    public void listen1(ConsumerRecord<?, ?> record) {
        System.out.println("已经被消费");
        log.info("kafka的key: " + record.key());
        log.info("kafka的value: " + record.value());
    }

}
