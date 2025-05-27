package com.basepackage.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDTO {

	  private Long uid;

	  @NotNull
	    @NotBlank(message = "Username is required")
	    private String userName;

	    @NotBlank(message = "firstname is required")
	    private String firstName;
	    
	    
	    @NotBlank(message = "lastname is required")
	    private String lastName;
	    
	    @NotBlank(message = "mobileno is required")
	    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
	    private String mobileNo;
	    
	    
	    @Email(message = "Email should be valid")
	    @NotBlank(message = "Username is required")
	    private String email;

	    
	    @NotBlank(message="password field is required")
	    @Size(min = 8, message = "Password must be at least 8 characters long")
	    private String password;
	    
	    // Optional: include logins if needed
	    private List<LoginDTO> login;


		public Long getUid() {
			return uid;
		}


		public void setUid(Long uid) {
			this.uid = uid;
		}


		public String getUserName() {
			return userName;
		}


		public void setUserName(String userName) {
			this.userName = userName;
		}


		public String getFirstName() {
			return firstName;
		}


		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}


		public String getLastName() {
			return lastName;
		}


		public void setLastName(String lastName) {
			this.lastName = lastName;
		}


		public String getMobileNo() {
			return mobileNo;
		}


		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public List<LoginDTO> getLogin() {
			return login;
		}


		public void setLogin(List<LoginDTO> login) {
			this.login = login;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}
	
	    
}
