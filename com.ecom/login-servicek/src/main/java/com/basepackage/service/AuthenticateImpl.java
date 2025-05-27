package com.basepackage.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.basepackage.controller.AuthController;
import com.basepackage.model.User;
import com.basepackage.repo.AuthenticationRepo;

@Service
public class AuthenticateImpl  implements AuthServiceI{

	                  @Autowired
	                  AuthenticationRepo authenticationRepo;
	                  
	                  @Autowired
	                  private  PasswordEncoder encoder;

	                  
	                  @Override
	                  public Optional<String> authenticate(String username, String password) {
	                      if (username == null || username.isBlank() || password == null || password.isBlank()) {
	                          return false;
	                      }

	                      // Retrieve user by username
	                      Optional<User> optionalUser = authenticationRepo.findByUserName(username);

	                      if (optionalUser.isEmpty()) {
	                          System.out.println("No user found with username: " + username);
	                          return false;
	                      }

	                      User user = optionalUser.get();

	                      // Check if password matches
	                      boolean passwordMatches = encoder.matches(password, user.getPassword());

	                      return passwordMatches;
	                  }



}
