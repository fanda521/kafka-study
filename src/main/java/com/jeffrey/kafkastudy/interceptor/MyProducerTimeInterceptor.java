package com.jeffrey.kafkastudy.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @version 1.0
 * @Aythor lucksoul 王吉慧
 * @date 2022/3/6 0:37
 * @description
 */
public class MyProducerTimeInterceptor implements ProducerInterceptor<String,String> {
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        //1.获取原始value
        String value = producerRecord.value();
        //2. 构造新的值
        value = System.currentTimeMillis() + "--" + value;
        //3. 返回消息
        return new ProducerRecord<>(producerRecord.topic(),producerRecord.partition(),producerRecord.key()
        ,value);
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
