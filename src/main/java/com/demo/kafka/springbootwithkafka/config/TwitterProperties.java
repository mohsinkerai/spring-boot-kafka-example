package com.demo.kafka.springbootwithkafka.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "my.twitter")
public class TwitterProperties {

  private String consumerKey;
  private String consumerSecret;
  private String token;
  private String tokenSecret;
}
