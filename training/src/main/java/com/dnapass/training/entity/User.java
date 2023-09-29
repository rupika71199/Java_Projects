package com.dnapass.training.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user200")
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String mobileNumber;  
	private String state;
	private String city;
	private String pinCode;
	private String role;
	private String username; 
	private String password;
	
	public User(Integer userId, String mobileNumber, String state, String city, String pinCode, String role,
			String name, String password) {
		super();
		this.id = userId;
		this.mobileNumber = mobileNumber;
		this.state = state;
		this.city = city;
		this.pinCode = pinCode;
		this.role = role;
		this.username = name;
		this.password = password;
		
	}
	
	public User(String mobileNumber, String state, String city, String pinCode, String role,
			String name, String password) {
		super();
		this.mobileNumber = mobileNumber;
		this.state = state;
		this.city = city;
		this.pinCode = pinCode;
		this.role = role;
		this.username = name;
		this.password = password;
		
	}
}
