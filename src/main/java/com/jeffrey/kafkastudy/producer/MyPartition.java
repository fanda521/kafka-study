package com.jeffrey.kafkastudy.producer;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @Aythor lucksoul 王吉慧
 * @date 2022/3/5 20:37
 * @description 自定义分区策略
 */
public class MyPartition implements Partitioner {
    private final AtomicInteger counter = new AtomicInteger(new Random().nextInt());
    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        System.out.println("s="+s);
        List<PartitionInfo> partitionInfos =
                cluster.availablePartitionsForTopic(s);
        int i = counter.addAndGet(2) ;
        int i1 = i % partitionInfos.size();
        if (i1 < 0) i1 = -i1;
        System.out.println("i = " + i +",i1 = "+i1);

        return i1;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {
        System.out.println("config="+new Gson().toJson(map));
    }
}
