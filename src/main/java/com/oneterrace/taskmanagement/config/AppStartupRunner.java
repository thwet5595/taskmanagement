package com.oneterrace.taskmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.oneterrace.taskmanagement.config.security.CustomPasswordEncoder;
import com.oneterrace.taskmanagement.model.User;
import com.oneterrace.taskmanagement.service.UserService;

@Component
public class AppStartupRunner implements ApplicationRunner {

	@Autowired
	UserService userService;

	@Autowired
	CustomPasswordEncoder passwordEncoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.createTask();
	}

	public void createTask() {
		System.out.println("Inside CreateTask()..");
		User user = new User();
		user.setEmail("first@gmail.com");
		String encryptedPwd = passwordEncoder.encode("task123");
		user.setPassword(encryptedPwd);
		user.setRole("ROLE_USER");
		this.userService.save(user);
	}
}
