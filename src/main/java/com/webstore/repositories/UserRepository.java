package com.webstore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.webstore.model.UserAccount;

public interface UserRepository extends CrudRepository<UserAccount, Long> {
	

}
