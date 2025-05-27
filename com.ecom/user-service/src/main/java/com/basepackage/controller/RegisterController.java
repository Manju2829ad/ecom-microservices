//package com.basepackage.controller;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.basepackage.dto.UserDTO;
//import com.basepackage.kafka.RegisterKafkaConsumer;
//import com.basepackage.kafka.RegisterKafkaProducer;
//import com.basepackage.service.RegisterUserI;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/api/user")
//public class RegisterController {
//
//    @Autowired
//    RegisterUserI registerUser;
//
//    
//    @Autowired
//    private RegisterKafkaProducer   producer;
//    
//    
//    
//    
//    @GetMapping("/getMessage")
//    public ResponseEntity<?>  getMessage(){
//    	
//    	 return ResponseEntity.status(HttpStatus.CONTINUE).body("Hello Wordld");
//    }
//    
//    
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDto, BindingResult bindingResult) {
//        try {
//        	
//        	System.out.println("inside the register controller ");
//            // Handle validation errors for the UserDTO
//            if (bindingResult.hasErrors()) {
//                return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
//            }
//
//            // Register the user via the service layer and get the result in an Optional
//			/* Optional<UserDTO> savedUser = registerUser.registerUser(userDto); */
//
//            // send the message to the producer 
//            
//            
//            
//            
//            if (savedUser.isPresent() && savedUser.get().getUid() != null) {
//                return ResponseEntity.status(HttpStatus.CREATED).body(savedUser.get());
//            } else {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body("Failed to save user. Please try again later.");
//            }
//
//        } catch (IllegalArgumentException e) {
//            // Bad input from client
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Bad request: " + e.getMessage());
//
//        } catch (DataIntegrityViolationException e) {
//            // Conflict, like duplicate email or username
//            return ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body("User already exists or violates unique constraints.");
//
//        } catch (NullPointerException e) {
//            // Should not usually happen, but handle it gracefully
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Unexpected null value encountered: " + e.getMessage());
//
//        } catch (Exception e) {
//            // Catch-all fallback
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("An unexpected error occurred: " + e.getMessage());
//        }
//    }
//}
