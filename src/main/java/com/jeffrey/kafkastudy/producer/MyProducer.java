package com.jeffrey.kafkastudy.producer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jeffrey.kafkastudy.entity.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @version 1.0
 * @Aythor jeffrey 王吉慧
 * @date 2022/2/18 18:13
 * @description
 */
@Slf4j
@Component
public class MyProducer {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send() {
        MyMessage message = new MyMessage();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", gson.toJson(message));
        System.out.println("+++++++++++++++++++++  message = " + gson.toJson(message));
        //topic-ideal为主题
        kafkaTemplate.send("topic-ideal", gson.toJson(message));
    }

}
