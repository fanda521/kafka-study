package com.jeffrey.kafkastudy.test;

import com.jeffrey.kafkastudy.producer.MyProducer;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
  @SpringBootTest
  public class KafkaProducerApplicationTests {

      @Resource
      private MyProducer myProducer;

      @Test
      public void kafkaProducer(){
          this.myProducer.send();
      }

      @Test
      public void contextLoads() {
      }

  }
