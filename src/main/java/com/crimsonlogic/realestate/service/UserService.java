package com.crimsonlogic.realestate.service;

import com.crimsonlogic.realestate.dto.EditUserProfileDTO;
import com.crimsonlogic.realestate.exception.UserRegistrationException;
import com.crimsonlogic.realestate.model.UserAuthentication;
import com.crimsonlogic.realestate.model.UserDetails;

public interface UserService {
	
	//register user
	 UserDetails registerUser(UserDetails userDetails, UserAuthentication userAuth) throws UserRegistrationException;
	 
	 //edit user profile
	 UserDetails editUserProfile(EditUserProfileDTO editUserProfileDTO) throws UserRegistrationException;
	
}
