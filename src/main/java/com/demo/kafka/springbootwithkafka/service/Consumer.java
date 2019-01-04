package com.demo.kafka.springbootwithkafka.service;

import java.io.IOException;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

  private final Logger logger = LoggerFactory.getLogger(Producer.class);

  // Reference to SpelEX
  // https://github.com/spring-projects/spring-kafka/issues/132
  @KafkaListener(topics = "${my.kafka.topic}", groupId = "my.kafka.group")
  public void consume(String message) throws IOException, InterruptedException {
    Thread.sleep(Math.abs(new Random().nextInt())%1000);
    logger.info(String.format("#### -> Consumed message -> %s", message));
  }
}
