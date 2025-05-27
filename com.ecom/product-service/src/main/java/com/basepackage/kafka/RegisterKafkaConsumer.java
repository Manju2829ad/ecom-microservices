//package com.basepackage.kafka;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//import org.modelmapper.ModelMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.validation.BindingResult;
//
//import com.basepackage.dto.UserDTO;
//import com.basepackage.events.RegisterEvent;
//import com.basepackage.model.User;
//import com.basepackage.repo.RegistrationRepo;
//import com.basepackage.service.RegisterUserI;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import jakarta.validation.Valid;
//
//@Service
//public class RegisterKafkaConsumer {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterKafkaConsumer.class);
//
//    @Autowired
//    private RegistrationRepo registrationRepo;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private RegisterUserI registerUser;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//    private final SimpMessagingTemplate messagingTemplate; // For WebSocket
//
//    public RegisterKafkaConsumer(KafkaTemplate<String, String> kafkaTemplate,
//                                 SimpMessagingTemplate messagingTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    @KafkaListener(topics = "login-topic", groupId = "registerUser-consumer")
//    public void consume(@Valid @Payload RegisterEvent event,
//                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
//                        BindingResult bindingResult) {
//
//        LOGGER.info("Consumed register event from topic '{}': {}", topic, event);
//
//        Map<String, Object> response = new HashMap<>();
//        String responseTopic = "register-response-topic";
//
//        try {
//            if (bindingResult.hasErrors()) {
//                Map<String, String> errors = new HashMap<>();
//                bindingResult.getFieldErrors()
//                             .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
//                response.put("status", "VALIDATION_FAILED");
//                response.put("errors", errors);
//            } else {
//                // Convert event to DTO
//                UserDTO userDto = modelMapper.map(event, UserDTO.class);
//
//                // Call service
//                Optional<UserDTO> savedDto = registerUser.registerUser(userDto);
//
//                if (savedDto.isPresent()) {
//                    response.put("status", "SUCCESS");
//                    response.put("user", savedDto.get());
//                } else {
//                    response.put("status", "FAILED");
//                    response.put("message", "User could not be registered");
//                }
//            }
//
//            // Send JSON response to Kafka response topic
//            String jsonResponse = objectMapper.writeValueAsString(response);
//            kafkaTemplate.send(responseTopic, event.getUid().toString(), jsonResponse);
//
//            // Also send it to WebSocket clients
//            messagingTemplate.convertAndSend("/topic/register", response);
//
//            LOGGER.info("Sent registration response to Kafka and WebSocket");
//
//        } catch (JsonProcessingException e) {
//            LOGGER.error("Failed to serialize registration response", e);
//        } catch (Exception e) {
//            LOGGER.error("Unexpected error in consumer", e);
//        }
//    }
//}
