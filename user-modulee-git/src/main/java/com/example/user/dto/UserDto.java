package com.example.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDto {
	
	
    private int userLoginId;
	
	@NotBlank(message = "firstName connot be blank!")
	private String firstName;
	
	@NotBlank(message = "lastName connot be blank!")
	private String lastName;
	
	@NotBlank(message = "userName connot be blank!")
	private String userName;
	
	@Email
	private String emailAddress;
	
	@NotBlank(message = "password connot be blank!")
	private String password;
	
	private String role;
	
	public UserDto() {
		super();
	}

	public UserDto( int userLoginId,
			 String firstName,
			 String lastName,
			String userName,
			 String emailAddress,  String password,
			String role) {
		super();
		this.userLoginId = userLoginId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.role = role;
	}

	public int getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(int userId) {
		this.userLoginId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String name) {
		this.userName = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String rol) {
		this.role = rol;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddres) {
		this.emailAddress = emailAddres;
	}
	
	
	

}
