package com.basepackage.service;

import java.util.Optional;

import com.basepackage.dto.UserDTO;

public interface RegisterUserI {

	
	
	Optional<UserDTO>    registerUser(UserDTO    userDto) throws NullPointerException,Exception;
	        
	
	                   
}
