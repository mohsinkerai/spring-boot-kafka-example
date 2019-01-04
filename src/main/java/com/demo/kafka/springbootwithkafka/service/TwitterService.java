package com.demo.kafka.springbootwithkafka.service;

import com.twitter.hbc.core.Client;
import java.util.concurrent.BlockingQueue;
import me.xdrop.jrand.JRand;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "producer.enabled")
public class TwitterService {

  private final Producer producer;
//  private final Client twitterClient;
//  private final BlockingQueue<String> twitterMsgQueue;

  public TwitterService(Producer producer
//      ,Client twitterClient,
//      @Qualifier("twitterMsgQueue") BlockingQueue<String> twitterMsgQueue
 ) {
    this.producer = producer;
//    this.twitterClient = twitterClient;
//    this.twitterMsgQueue = twitterMsgQueue;
  }

  @Scheduled(fixedDelay = 1000L)
  public void publishQueueMessages() throws InterruptedException {
//    while (!twitterClient.isDone()) {
//      String msg = twitterMsgQueue.take();
//      producer.sendMessage(msg);
//    }
    for (int i = 0; i < 10; i++) {
      producer.sendMessage(JRand.lorem().sentences(1, 3).gen());
    }
  }
}
