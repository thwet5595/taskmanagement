package com.oneterrace.taskmanagement.service;

import com.oneterrace.taskmanagement.model.User;

public interface UserService {
	User findByEmail(String email);
}
