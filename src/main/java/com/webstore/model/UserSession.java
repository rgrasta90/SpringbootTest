package com.webstore.model;

import org.springframework.context.annotation.Scope;

@Scope("session")
public class UserSession {
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
