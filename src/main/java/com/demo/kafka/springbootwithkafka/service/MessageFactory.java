package com.demo.kafka.springbootwithkafka.service;

import com.thedeanda.lorem.LoremIpsum;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "producer.enabled")
public class MessageFactory {

  private final Producer producer;
  private final LoremIpsum instance;

  public MessageFactory(Producer producer
  ) {
    instance = LoremIpsum.getInstance();
    this.producer = producer;
  }

  @Scheduled(fixedDelay = 100L)
  public void publishQueueMessages() throws InterruptedException {
    producer.sendMessage(instance.getWords(4, 12));
  }
}
