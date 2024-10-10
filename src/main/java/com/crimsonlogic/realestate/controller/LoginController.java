package com.crimsonlogic.realestate.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.realestate.dto.LoginRequestDTO;
import com.crimsonlogic.realestate.exception.UserAuthenticationException;
import com.crimsonlogic.realestate.model.UserAuthentication;
import com.crimsonlogic.realestate.service.LoginService;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api/users")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginServ;

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDTO loginRequest) {
		Map<String, Object> response = new HashMap<>();
		try {
			UserAuthentication userAuth = loginServ.login(loginRequest);
			response.put("email", userAuth.getEmail());
			response.put("userRole", userAuth.getUserRole().getRole()); // Include role information
			response.put("userDetails", userAuth.getUserDetails());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (UserAuthenticationException e) {
			logger.error("Authentication failed: {}", e.getMessage());
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
	}
}
