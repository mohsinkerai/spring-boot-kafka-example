package com.demo.kafka.springbootwithkafka.service;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "consumer.enabled")
public class Consumer {

  private final Logger logger = LoggerFactory.getLogger(Producer.class);

  // Reference to SpelEX
  // https://github.com/spring-projects/spring-kafka/issues/132
  @KafkaListener(topics = "${my.kafka.topic}", groupId = "my.kafka.group")
  public void consume(List<String> messages,
      @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
      @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws IOException, InterruptedException {
    logger.info("Received {} Messages in Partition {} and Offet {}", messages.size(), partitions,
        offsets);
    for (String message : messages) {
      logger.info(String.format("#### -> Consumed message -> %s", message));
    }
  }
}
