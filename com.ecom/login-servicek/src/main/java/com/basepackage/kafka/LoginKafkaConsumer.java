package com.basepackage.kafka;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.basepackage.events.LoginEvent;
import com.basepackage.model.Login;
import com.basepackage.model.User;
import com.basepackage.repo.AuthenticationRepo;
import com.basepackage.repo.LoginRepoI;
import com.basepackage.service.AuthServiceI;

@Service
public class LoginKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginKafkaConsumer.class);

    private final AuthServiceI authService;
    private final AuthenticationRepo authenticationRepo;
    private final LoginRepoI loginRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ModelMapper modelMapper;

    public LoginKafkaConsumer(AuthServiceI authService,
                              AuthenticationRepo authenticationRepo,
                              LoginRepoI loginRepo) {
        this.authService = authService;
        this.authenticationRepo = authenticationRepo;
        this.loginRepo = loginRepo;
    }

    @KafkaListener(topics = "login-topic", groupId = "login-consumer")
    public void consume(@Payload LoginEvent event,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

    	System.out.println("inside the consumer");
        LOGGER.info("Consumed login event from topic '{}': {}", topic, event);

        String identifier = event.getUsername() != null ? event.getUsername() : event.getEmail();

        Optional<User> userOpt;
        if (identifier.contains("@")) {
            userOpt = authenticationRepo.findByUserName(event.getEmail());
        } else {
            userOpt = authenticationRepo.findByUserName(event.getUsername());
        }

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            boolean passwordMatches = encoder.matches(event.getPassword(), user.getPassword());

            if (passwordMatches) {
                // Map event to Login entity
                Login login = modelMapper.map(event, Login.class);
               // login.s(user); // important: set user to maintain FK relation
                loginRepo.save(login);
                LOGGER.info("Login record saved successfully for user: {}", identifier);
            } else {
                LOGGER.warn("Password mismatch for user: {}", identifier);
            }
        } else {
            LOGGER.warn("User not found: {}", identifier);
        }
    }
}
