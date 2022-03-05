package com.jeffrey.kafkastudy.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @version 1.0
 * @Aythor lucksoul 王吉慧
 * @date 2022/3/6 0:41
 * @description
 */
public class MyProducerCountInterceptor implements ProducerInterceptor<String , String> {
    int success =0;
    int fail =0;
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
    if (recordMetadata != null)
        {
            success ++ ;
        }
    else {
        fail++;
    }

    }

    @Override
    public void close() {
        System.out.println("success:"+success);
        System.out.println("fail:" + fail);
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
