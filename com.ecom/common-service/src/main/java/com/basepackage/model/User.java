package com.basepackage.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name="user_details")
@Getter
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;
	
	private String userName;
	
	
	private String  firstName;
	private String lastName;
	private String mobileNo;
	private String email;
	
	private String password;
	
//	@JsonManagedReference(value="orders-users")
//	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
//	private List<Order> orders;
//	
	

	
    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNo=" + mobileNo +
                ", email='" + email + '\'' +
                ", email='" + password+ '\'' +
                // Avoid printing addresses to prevent recursion
                '}';
    }
    
    
    
    public Long getUid() {
		return uid;
	}



	public void setUid(Long uid) {
		this.uid = uid;
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



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
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



	public List<Login> getLogin() {
		return login;
	}



	public void setLogin(List<Login> login) {
		this.login = login;
	}



	@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Login> login;
    
	
}
