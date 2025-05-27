package com.basepackage.config;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncrypter {

    private final PasswordEncoder encoder;

    public PasswordEncrypter(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    /**
     * Hashes the raw password using BCrypt.
     */
    public String encrypt(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * Verifies that a raw password matches the hashed one.
     */
    public boolean matches(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}
