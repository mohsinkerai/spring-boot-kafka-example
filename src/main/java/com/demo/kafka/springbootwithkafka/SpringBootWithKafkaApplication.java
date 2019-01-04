package com.demo.kafka.springbootwithkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBootWithKafkaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootWithKafkaApplication.class, args);
  }
}

