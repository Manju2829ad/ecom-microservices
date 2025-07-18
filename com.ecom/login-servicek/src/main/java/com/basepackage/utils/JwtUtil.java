package com.basepackage.utils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import javax.crypto.SecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtUtil {

	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    		  
    		  
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes()); // Explicitly return SecretKey
    }

    
    public String generateToken(String username, List<String> roles) {
        logger.info("Generating token for user: {}", username,roles);
        

        Map<String, Object> claims = Map.of("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(username)
                .claims(claims)
                .issuedAt(now)
                .expiration(validity)
                .signWith(getSigningKey())  // Works fine as signWith accepts Key
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            logger.debug("Validating token...");

            JwtParser parser = Jwts.parser()
                .verifyWith(getSigningKey())  // Now works since getSigningKey returns SecretKey
                .build();
            parser.parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            logger.error("Invalid token: {}", token, e);

            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            JwtParser parser = Jwts.parser()
                .verifyWith(getSigningKey())
                .build();
            return parser.parseSignedClaims(token)
                .getPayload()
                .getSubject();
            
            
        } catch (Exception e) {
            return null;
        }
    }

    
    
    public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
        try {
        	   logger.debug("getting roles from token..");
        	   
        	   logger.info("getting roles from token..",token);
        	   
            JwtParser parser = Jwts.parser()
                .verifyWith(getSigningKey())
                .build();
            var claims = parser.parseSignedClaims(token).getPayload();
            List<?> roles = (List<?>) claims.get("roles");
            return roles != null ? roles.stream()
                .map(Object::toString)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList())
                : Collections.emptyList();
        } catch (Exception e) {
        	
        	logger.error(token,e);
            return Collections.emptyList();
        }
    }

    public Cookie generateCookie(String token) {
    	
    	logger.info("generating the cookie",token);
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);
        return cookie;
    }
}