package com.demo.kafka.springbootwithkafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

  private static final Logger logger = LoggerFactory.getLogger(Producer.class);

  private final String TOPIC;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public Producer(
      @Value("${my.kafka.topic}") String topic,
      KafkaTemplate<String, String> kafkaTemplate) {
    TOPIC = topic;
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String message) {
    logger.info(String.format("#### -> Producing message -> %s", message));
    for (int i = 0; i < 10; i++) {
      this.kafkaTemplate.send(TOPIC, i + message);
    }
  }
}
