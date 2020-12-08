package com.oneterrace.taskmanagement.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneterrace.taskmanagement.common.Configurations;
import com.oneterrace.taskmanagement.model.User;
import com.oneterrace.taskmanagement.repository.UserRepository;
import com.oneterrace.taskmanagement.service.UserService;

@RequestMapping("terrace")
@RestController
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;
	// @Autowired
	// LoginRepository loginRepository;
	@Autowired
	UserService userService;
	@Autowired
	TokenStore tokenStore;

	@Autowired
	HttpServletRequest httpServletRequest;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/user/currentUser")
	public ResponseEntity<User> getUserByEmailAndPassword(@RequestBody User user, HttpServletRequest request) {
		System.out.println("Inside GetUserByEmailAndPassword..");

		User userData = userRepository.findByEmail(user.getEmail());

		if ((userData != null)) {
			//userData.setSessionId(UUID.randomUUID().toString());
			//userRepository.save(userData);
			return new ResponseEntity<>(userData, HttpStatus.OK);
		} else {
			return new ResponseEntity("Invalid User Id", HttpStatus.UNAUTHORIZED);
		}
	}
//	
//	@PostMapping("/logout")
//	public ResponseEntity<?> logOut(@RequestParam("email") String email) {
//		User user = userService.findByEmail(email);
//	
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

}
