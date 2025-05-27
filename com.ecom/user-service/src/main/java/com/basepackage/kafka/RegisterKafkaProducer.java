package com.basepackage.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.protocol.Message;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.basepackage.events.RegisterEvent;

@Service
public class RegisterKafkaProducer {

	private static final org.slf4j.Logger     Logger=LoggerFactory.getLogger(RegisterKafkaProducer.class);
	  
	private final KafkaTemplate<String, RegisterEvent> kafkaTemplate ;
	
	private  NewTopic  topic;

  
	public RegisterKafkaProducer(KafkaTemplate<String, RegisterEvent> kafkaTemplate ) {
		this.kafkaTemplate=kafkaTemplate;
	}
	
	
     public           void sendRegisterEvent(RegisterEvent   registerEvent) {
        	   
        	   Logger.info(String.format("Order event=>%s",registerEvent.toString() ));
        	   
        	   // create message 

        	   org.springframework.messaging.Message<RegisterEvent> message = MessageBuilder.
        			   withPayload(registerEvent)
        			   .setHeader(KafkaHeaders.TOPIC,topic.name()).build();
        	   
        	   kafkaTemplate.send(message);
           }
	
}
