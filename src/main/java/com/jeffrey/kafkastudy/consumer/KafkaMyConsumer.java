package com.jeffrey.kafkastudy.consumer;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @version 1.0
 * @Aythor lucksoul 王吉慧
 * @date 2022/3/5 23:32
 * @description 消费者
 */
public class KafkaMyConsumer {
    public static void main(String[] args) {
        //1.创建消费者配置信息

        Properties pro = new Properties();

        //连接的集群
        pro.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092,localhost:9093,localhost:9094");
        //开启自动提交
        pro.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        //自动提交延时
        pro.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,1000);
        //key value 反序列化
        pro.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        pro.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");

        //消费者组
        pro.put(ConsumerConfig.GROUP_ID_CONFIG,"jeffrey");

        //2.创建消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(pro);

        //3.订阅主题
        consumer.subscribe(Arrays.asList("kafka-test","test"));

        //4.获取数据
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord record : records) {
                System.out.println(new Gson().toJson(record));
            }
        }


        //5.关闭连接
        //consumer.close();
    }
}
