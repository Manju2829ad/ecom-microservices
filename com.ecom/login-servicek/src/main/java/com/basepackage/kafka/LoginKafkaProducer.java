package com.basepackage.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.protocol.Message;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.basepackage.events.LoginEvent;



@Service

public class LoginKafkaProducer {

	private static final org.slf4j.Logger     Logger=LoggerFactory.getLogger(LoginKafkaProducer.class);
	  
	private final KafkaTemplate<String, LoginEvent> kafkaTemplate ;
	
	private  NewTopic  topic;

  
	public LoginKafkaProducer(KafkaTemplate<String, LoginEvent> kafkaTemplate ) {
		this.kafkaTemplate=kafkaTemplate;
	}
	
	
           public void sendLoginEvent(LoginEvent loginEvent) {
        	   
        	   Logger.info(String.format("Order event=>%s",loginEvent.toString() ));
        	   
        	   // create message 

        	   org.springframework.messaging.Message<LoginEvent> message = MessageBuilder.
        			   withPayload(loginEvent)
        			   .setHeader(KafkaHeaders.TOPIC,topic.name()).build();
        	   
        	   kafkaTemplate.send(message);
           }
	
}
