package com.basepackage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basepackage.dto.LoginDTO;
import com.basepackage.model.User; // Assuming you have User model with getId()
import com.basepackage.service.AuthServiceI;
import com.basepackage.service.UserServiceI; // or UserRepository if you fetch user details
import com.basepackage.utils.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user/")
public class AuthController {

    @Autowired
    private AuthServiceI authService;

   
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDto,
                                        HttpServletResponse httpServletResponse,
                                        BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors().get(0).getDefaultMessage());
        }

        boolean isAuthenticated = authService.authenticate(loginDto.getUsername(), loginDto.getPassword());
        if (isAuthenticated) {
            // Fetch the full user details to get user ID
            User user = userService.findByUsername(loginDto.getUsername());
            if (user == null) {
                return ResponseEntity.status(404).body("User not found");
            }

            // Generate JWT with user ID embedded in claims
            String token = jwtUtil.generateToken(user.getUsername(), List.of("User"), user.getId());
            Cookie cookie = jwtUtil.generateCookie(token);
            httpServletResponse.addCookie(cookie);

            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.status(401).body("Authentication failed: Invalid credentials");
        }
    }
}
