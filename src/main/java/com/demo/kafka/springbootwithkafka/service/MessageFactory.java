package com.demo.kafka.springbootwithkafka.service;

import me.xdrop.jrand.JRand;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "producer.enabled")
public class MessageFactory {

  private final Producer producer;

  public MessageFactory(Producer producer
  ) {
    this.producer = producer;
  }

  @Scheduled(fixedDelay = 1000L)
  public void publishQueueMessages() throws InterruptedException {
    for (int i = 0; i < 10; i++) {
      producer.sendMessage(JRand.lorem().sentences(1, 3).gen());
    }
  }
}
