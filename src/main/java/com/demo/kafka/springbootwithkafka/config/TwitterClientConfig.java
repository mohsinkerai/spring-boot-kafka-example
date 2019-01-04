package com.demo.kafka.springbootwithkafka.config;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwitterClientConfig {

  @Bean(initMethod = "connect", destroyMethod = "stop")
  public Client createTwitterClient(@Qualifier("twitterMsgQueue") BlockingQueue<String> msgQueue,
      TwitterProperties twitterProperties) {

    /** Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth) */
    Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
    StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
    // Optional: set up some followings and track terms
    List<Long> followings = Lists.newArrayList(1234L, 566788L);
    List<String> terms = Lists.newArrayList("twitter", "api");
    hosebirdEndpoint.followings(followings);
    hosebirdEndpoint.trackTerms(terms);

    // These secrets should be read from a config file
    Authentication hosebirdAuth = new OAuth1(twitterProperties.getConsumerKey(),
        twitterProperties.getConsumerSecret(), twitterProperties.getToken(),
        twitterProperties.getTokenSecret());

    ClientBuilder builder = new ClientBuilder()
        .name("Hosebird-Client-01")                              // optional: mainly for the logs
        .hosts(hosebirdHosts)
        .authentication(hosebirdAuth)
        .endpoint(hosebirdEndpoint)
        .processor(new StringDelimitedProcessor(msgQueue));

    return builder.build();
  }

  @Bean
  public BlockingQueue<String> twitterMsgQueue() {
    /** Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream */
    return new LinkedBlockingQueue<>(100000);
  }
}
