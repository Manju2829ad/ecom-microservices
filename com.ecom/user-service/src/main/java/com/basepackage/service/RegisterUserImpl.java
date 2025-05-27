package com.basepackage.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.basepackage.dto.UserDTO;
import com.basepackage.model.User;
import com.basepackage.repo.RegistrationRepo;

@Service
public class RegisterUserImpl implements RegisterUserI {

    @Autowired
     private RegistrationRepo registrationrepo;

    @Autowired
    private ModelMapper modelmapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder ;
    @Override
    public Optional<UserDTO> registerUser(UserDTO userDto) {
        // Create a map of the fields to check for null or blank values
        Map<String, Object> fieldsToCheck = Map.of(
            "username", userDto.getUserName(),
            "firstname", userDto.getFirstName(),
            "lastname", userDto.getLastName(),
            "email", userDto.getEmail(),
            "mobileNo", userDto.getMobileNo()
        );

        // List of missing or blank fields
        List<String> missingFields = fieldsToCheck.entrySet().stream()
            .filter(entry -> entry.getValue() == null || entry.getValue().toString().isBlank())
            .map(Map.Entry::getKey)
            .toList();

        // If any fields are missing or blank, throw an exception
        if (!missingFields.isEmpty()) {
            throw new IllegalArgumentException("Missing or blank mandatory fields: " + missingFields);
        }

        // Convert UserDTO to User entity
        User user = modelmapper.map(userDto, User.class);

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        
        // Save the user to the repository
        User registeredUser = registrationrepo.save(user);

        // If registration is successful, map the User entity to UserDTO
        if (registeredUser != null) {
            UserDTO registeredDto = modelmapper.map(registeredUser, UserDTO.class);
            return Optional.of(registeredDto);  // Return the registered UserDTO in an Optional
        } else {
            return Optional.empty();  // Return an empty Optional if registration fails
        }
    }
}
