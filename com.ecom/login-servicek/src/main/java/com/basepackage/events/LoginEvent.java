package com.basepackage.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder 

public class LoginEvent {

	
	private Long id;
	
	private String username;
	
	private String email;
	
	
	private  long  timestamp;
	
	
	public LoginEvent(String username, String password, long timeMillis) {
		this.username=username;
		this.password=password;
		this.timestamp= timeMillis;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	private String password;
	
	
	@Override
	public String toString() {
		
        return "LoginEvent [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + "]";
	}
	
}
