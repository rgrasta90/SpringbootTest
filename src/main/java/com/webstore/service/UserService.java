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

	public UserAccount userExists(String name) {
		UserAccount u = this.repository.findByName(name);
		return u;
	}
	
	public UserAccount login(String username, String password){
	return this.repository.findByNameAndPassword(username, password);
	}
	

}
