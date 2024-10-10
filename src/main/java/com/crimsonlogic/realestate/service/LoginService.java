package com.crimsonlogic.realestate.service;

import com.crimsonlogic.realestate.dto.LoginRequestDTO;
import com.crimsonlogic.realestate.exception.UserAuthenticationException;
import com.crimsonlogic.realestate.model.UserAuthentication;

public interface LoginService {

	UserAuthentication login(LoginRequestDTO loginRequest) throws UserAuthenticationException;
}
