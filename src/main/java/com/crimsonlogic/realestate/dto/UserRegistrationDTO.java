package com.crimsonlogic.realestate.dto;

import com.crimsonlogic.realestate.model.UserAuthentication;
import com.crimsonlogic.realestate.model.UserDetails;

public class UserRegistrationDTO {
	private UserDetails userDetails;
    private UserAuthentication userAuth;
    
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public UserAuthentication getUserAuth() {
		return userAuth;
	}
	public void setUserAuth(UserAuthentication userAuth) {
		this.userAuth = userAuth;
	}
    
    
}
