package com.demo.kafka.springbootwithkafka.service;

import com.twitter.hbc.core.Client;
import java.util.concurrent.BlockingQueue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TwitterService {

  private final Producer producer;
  private final Client twitterClient;
  private final BlockingQueue<String> twitterMsgQueue;

  public TwitterService(Producer producer,
      Client twitterClient,
      @Qualifier("twitterMsgQueue") BlockingQueue<String> twitterMsgQueue) {
    this.producer = producer;
    this.twitterClient = twitterClient;
    this.twitterMsgQueue = twitterMsgQueue;
  }

  @Scheduled(fixedDelay = 100L)
  public void publishQueueMessages() throws InterruptedException {
    while (!twitterClient.isDone()) {
      String msg = twitterMsgQueue.take();
      producer.sendMessage(msg);
    }
  }
}
