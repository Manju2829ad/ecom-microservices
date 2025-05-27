package com.basepackage.filter;

import java.io.IOException;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.basepackage.model.User;
import com.basepackage.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter  {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
	


	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		// Get jwt from  the cookie 
		
		String  token = null;
		
		if(request.getCookies()!=null) {
			
			for(Cookie cookie: request.getCookies()) {
				
				if("jwt".equals(cookie.getName())) {
					token= cookie.getValue();
					break;
				}
				
			}
		}

		// validate and set auth context 
		if(token!=null&&jwtUtil.validateToken(token) ) {
			
			String username=jwtUtil.getUsernameFromToken(token);
			var authorities =jwtUtil.getRolesFromToken(token);
			
			
			var auth =  new UsernamePasswordAuthenticationToken(username,null,authorities);
			
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);

		}
        filterChain.doFilter(request, response);

	}

	
	
}
