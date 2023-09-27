package com.example.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"userName"})})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userLoginId;
	
	@NotNull(message = "Firstname cannot be null!")
	@NotBlank(message = "Firstname connot be blank!")
	private String firstName;
	
	@NotNull(message = "Lastname cannot be null!")
	@NotBlank(message = "Lastname connot be blank!")
	private String lastName;
	
	@NotNull(message = "Username cannot be null!")
	@NotBlank(message = "Username connot be blank!")
	private String userName;
	
	
	@Email
	private String emailAddress;
	
	private String password;
	
	private String role;
	
	public User(){
		super();
	}

	

	public User(int userLoginId,
			@NotNull(message = "Firstname cannot be null!") @NotBlank(message = "Firstname connot be blank!") String firstName,
			@NotNull(message = "Lastname cannot be null!") @NotBlank(message = "Lastname connot be blank!") String lastName,
			@NotNull(message = "Username cannot be null!") @NotBlank(message = "Username connot be blank!") String userName,
			@Email String emailAddress, String password) {
		super();
		this.userLoginId = userLoginId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.emailAddress = emailAddress;
		this.password = password;
	}



	public int getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(int userLoginId) {
		this.userLoginId = userLoginId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public boolean isPresent() {
		return false;
	}
	
	

	
}