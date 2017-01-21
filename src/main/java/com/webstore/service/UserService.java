package com.webstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webstore.model.UserAccount;
import com.webstore.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	public boolean validateUser(UserAccount u){
		
		return true;
	}
	
	public UserAccount createUser(UserAccount u){
		
		return this.repository.save(u);
	}
}
