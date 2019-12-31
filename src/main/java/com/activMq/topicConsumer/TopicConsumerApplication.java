package com.activMq.topicConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class TopicConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopicConsumerApplication.class, args);
	}

}
