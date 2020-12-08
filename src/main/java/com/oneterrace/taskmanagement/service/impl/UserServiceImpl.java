package com.oneterrace.taskmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.oneterrace.taskmanagement.model.User;
import com.oneterrace.taskmanagement.repository.UserRepository;
import com.oneterrace.taskmanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	@Override
	public void save(User user) {
		this.userRepository.save(user);
	}

	@Override
	public Authentication authenticate(String username, String password) throws Exception {
		Authentication auth = null;
		try {
			auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {
			// LOGGER.debug("USER_DISABLED");
		} catch (BadCredentialsException bce) {
			throw new BadCredentialsException("Incorrect Email or Password!");
		} catch (LockedException e) {
			// TODO: this user is locked! && Don't delete
			throw new LockedException(e.getMessage());
		}
		return auth;
	}
}
