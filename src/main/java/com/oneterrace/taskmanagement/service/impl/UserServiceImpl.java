package com.oneterrace.taskmanagement.service.impl;

import org.springframework.stereotype.Service;

import com.oneterrace.taskmanagement.model.User;
import com.oneterrace.taskmanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public User findByEmail(String email) {
		return null;
	}
}
