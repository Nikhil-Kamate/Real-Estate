package com.crimsonlogic.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.realestate.dto.EditUserProfileDTO;
import com.crimsonlogic.realestate.dto.UserRegistrationDTO;
import com.crimsonlogic.realestate.exception.UserRegistrationException;
import com.crimsonlogic.realestate.model.UserDetails;
import com.crimsonlogic.realestate.service.UserService;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserDetails> registerUser(@RequestBody UserRegistrationDTO registrationDTO) throws UserRegistrationException {
	    UserDetails createdUser = userService.registerUser(registrationDTO.getUserDetails(), registrationDTO.getUserAuth());
	    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/edit-profile")
    public ResponseEntity<UserDetails> editUserProfile(@RequestBody EditUserProfileDTO editUserProfileDTO) throws UserRegistrationException {
        UserDetails updatedUser = userService.editUserProfile(editUserProfileDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

}
