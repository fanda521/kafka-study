package com.jeffrey.kafkastudy.producer;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * @version 1.0
 * @Aythor lucksoul 王吉慧
 * @date 2022/2/26 10:27
 * @description  配置的形式 指定分区
 */
public class MyProducerConfCallBackMyPartition {

    public static void main(String[] args) {
        //1. 创建kafka 生产者的配置信息
        Properties properties = new Properties();

        //指定连接的kafka集群

        //properties.put("bootstrap.servers","localhost:9092");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092,localhost:9093,localhost:9094");
        //指定ack 应答级别
        //properties.put("acks","all");
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        //指定 重试次数
        properties.put("retries",3);

        //指定批次大小
        //如果看到轮训的接口需要将批次设置为1，否则看不到效果
        properties.put("batch.size",1);

        //指定等待时间
        properties.put("linger.ms",1);

        //指定RecordAccumulator缓冲区大小
        properties.put("buffer.memory",33554432);

        //指定 key value  序列化
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        //自定义分区
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.jeffrey.kafkastudy.producer.MyPartition");

        //2. 创建kafka 生产者对象
        KafkaProducer kafkaProducer = new KafkaProducer<>(properties);


        //3. 发送消息
        for (int i = 0; i < 10 ; i++) {
            kafkaProducer.send(new ProducerRecord<String, String>("kafka-test", "lucksoul" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println(new Gson().toJson(recordMetadata));
                }
            });
        }
        //4. 关闭资源
        kafkaProducer.close();
    }
}
