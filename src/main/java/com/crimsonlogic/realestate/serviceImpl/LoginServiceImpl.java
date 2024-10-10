package com.crimsonlogic.realestate.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.crimsonlogic.realestate.dto.LoginRequestDTO;
import com.crimsonlogic.realestate.exception.UserAuthenticationException;
import com.crimsonlogic.realestate.model.UserAuthentication;
import com.crimsonlogic.realestate.repository.UserAuthenticationRepository;
import com.crimsonlogic.realestate.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    @Override
    public UserAuthentication login(LoginRequestDTO loginRequest) throws UserAuthenticationException {
        logger.info("Attempting login with email: {}", loginRequest.getEmail());
        
        // Fetch user by email
        UserAuthentication userAuth = userAuthenticationRepository.findByEmail(loginRequest.getEmail());
        
        if (userAuth == null) {
            logger.info("No user found with this email: {}", loginRequest.getEmail());
            throw new UserAuthenticationException("User Not Found");
        }

        // Check if password matches (using BCrypt for comparison)
        if (!BCrypt.checkpw(loginRequest.getPassword(), userAuth.getPassword())) {
            logger.info("Invalid password for email: {}", loginRequest.getEmail());
            throw new UserAuthenticationException("Invalid email or password");
        }

        return userAuth; // Return the user authentication object if the password matches
    }
}