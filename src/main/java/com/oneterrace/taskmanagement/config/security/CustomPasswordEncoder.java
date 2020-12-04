package com.oneterrace.taskmanagement.config.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		String hashed = BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(12));
		return hashed;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
	}

	public static void main(String[] args) {
		String password = "task123";
		System.out.println(" Password...." + password);
		CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
		
		System.out.println("Encode .." + customPasswordEncoder.encode(password) + "<<<Length>>>"
				+ customPasswordEncoder.encode(password).length());
	}
}
