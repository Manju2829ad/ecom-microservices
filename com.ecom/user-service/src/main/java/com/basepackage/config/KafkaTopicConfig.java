package com.basepackage.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;

public class KafkaTopicConfig {

	@Bean
	public  NewTopic  loginTopic() {
		
		return new NewTopic("register-topic",1,(short)1);
	}
}
