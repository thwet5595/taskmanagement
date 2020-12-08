package com.oneterrace.taskmanagement.service;

import org.springframework.security.core.Authentication;

import com.oneterrace.taskmanagement.model.User;

public interface UserService {
	User findByEmail(String email);

	void save(User user);

	Authentication authenticate(String username, String password) throws Exception;
}
