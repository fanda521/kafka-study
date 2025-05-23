package com.jeffrey.kafkastudy.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @version 1.0
 * @Aythor lucksoul 王吉慧
 * @date 2022/2/26 10:27
 * @description  配置的形式
 */
public class MyProducerConf {

    public static void main(String[] args) {
        //1. 创建kafka 生产者的配置信息
        Properties properties = new Properties();

        //指定连接的kafka集群
        //单机的情况就指定一台机器 localhost:9092
        properties.put("bootstrap.servers","localhost:9092,localhost:9093,localhost:9094");

        //指定ack 应答级别
        properties.put("acks","all");

        //指定 重试次数
        properties.put("retries",3);

        //指定批次大小
        properties.put("batch.size",16384);

        //指定等待时间
        properties.put("linger.ms",1);

        //指定RecordAccumulator缓冲区大小
        properties.put("buffer.memory",33554432);

        //指定 key value  序列化
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");


        //2. 创建kafka 生产者对象
        KafkaProducer kafkaProducer = new KafkaProducer<>(properties);

        //3. 发送消息
        for (int i = 0; i < 10 ; i++) {
            kafkaProducer.send(new ProducerRecord<String, String>("topic-ideal","jeffrey" + i));
        }
        //4. 关闭资源
        kafkaProducer.close();
    }
}
