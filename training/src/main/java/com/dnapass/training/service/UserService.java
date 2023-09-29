package com.dnapass.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnapass.training.entity.User;
import com.dnapass.training.repository.UserRepository;

@Service
public class UserService{
	
	@Autowired
	UserRepository userRepo;
	//To add new user
	public User saveUser(User user) {
		User saveduser = new User(user.getId(),user.getMobileNumber(),user.getState(),user.getCity(), 
				user.getPinCode(),user.getRole() ,user.getUsername(), user.getPassword());
		userRepo.save(saveduser);
		return saveduser;
	}

	//To find user based on user id 
	public User findUser(Integer userId) {
		User findingUser = userRepo.findById(userId).get();
		return findingUser;
	}

}
